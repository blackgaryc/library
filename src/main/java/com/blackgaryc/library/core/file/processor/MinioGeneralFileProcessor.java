package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.error.FileProcessorNotSupportException;
import com.blackgaryc.library.core.mq.resut.Record;
import com.blackgaryc.library.core.mq.resut.S3;
import com.blackgaryc.library.core.mq.resut.S3Notify;
import com.blackgaryc.library.tools.StringTools;
import io.minio.*;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

public class MinioGeneralFileProcessor implements IGeneralFileProcessor<MinioFileInfo> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Tika tika;

    public MinioGeneralFileProcessor(Tika tika, MinioClient minioClient) {
        this.tika = tika;
        this.minioClient = minioClient;
    }

    private final MinioClient minioClient;

    @Override
    public IFileProcessBaseResult process(MinioFileInfo fileInfo) throws FileProcessorNotSupportException {
        if (fileInfo.getInfo() instanceof Record record) {
            try {
                S3 s3 = record.getS3();
                //data
                String bucketName = s3.getBucket().getName();
                //data
                String objectKey = URLDecoder.decode(s3.getObject().getKey(), Charset.defaultCharset());
                String[] split = objectKey.split("/");
                //data
                String uid = split.length > 2 ? split[1] : "";
                //data
                String md5 = s3.getObject().getETag();
                // get object
                GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectKey).build());
                Path tmpFile = Files.createTempFile("SPRING_LIBRARY", ".tmp");
                File file = tmpFile.toFile();
                file.deleteOnExit();
                FileOutputStream outputStream = new FileOutputStream(file);
                IOUtils.copy(object, outputStream);
                object.close();
                outputStream.close();

                String originFilename = Paths.get(objectKey).toFile().getName();
                //data
                String mimetype = tika.detect(file);
                String[] filenameSplit = StringTools.splitFilename2NameAndExtension(originFilename);
                // set data
                FileProcessBaseResult result = new FileProcessBaseResult();
                result.setFilename(filenameSplit[0]);
                result.setExtension(filenameSplit[1]);
                result.setObjectKey(objectKey);
                result.setUploadUid(uid);
                result.setMd5(getFileMd5(md5, file));
                result.setSize(file.length());
                result.setMimetype(mimetype);
                // do more process
                result = moreProcess(file, result);
                log.info(result.toString());
                return result;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("null args");
    }

    private FileProcessBaseResult moreProcess(File file, FileProcessBaseResult result) throws FileProcessorNotSupportException {
        // find processor by file extension
        IFileProcessor instance = FileProcessorFactory.getInstance(result.getExtension());
        if (instance == null) {
            //find processor by file mimetype
            instance = FileProcessorFactory.getInstance(result.getMimetype());
            assert null != instance;
        }
        return instance.process(file, result);
    }

    @Override
    public boolean support(Class<MinioFileInfo> clazz) {
        return MinioFileInfo.class.equals(clazz);
    }

    private String getFileMd5(String md5, File file) {
        if (null != md5 && !md5.isBlank()) {
            return md5;
        }
        try (FileInputStream in = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(in)) {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[4096];
            while (bufferedInputStream.available() > 0) {
                int read = bufferedInputStream.read(bytes);
                messageDigest.update(bytes, 0, read);
            }
            byte[] digest = messageDigest.digest();
            return StringTools.bytes2HexString(digest);
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
