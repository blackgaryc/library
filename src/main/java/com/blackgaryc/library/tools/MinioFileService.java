package com.blackgaryc.library.tools;

import com.blackgaryc.library.core.minio.MinioProperty;
import com.blackgaryc.library.core.minio.ObjectKeyPrefixEnum;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class MinioFileService {

    @Resource
    Tika tika;
    @Resource
    MinioClient minioClient;
    @Resource
    MinioProperty minioProperty;
    TikaConfig config = TikaConfig.getDefaultConfig();
    public ObjectWriteResponse uploadFile(ObjectKeyPrefixEnum objectKeyPrefixEnum, Path tempFile) throws IOException, ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, MimeTypeException {
        String detect = tika.detect(tempFile);
        MimeType mimeType = config.getMimeRepository().forName(detect);
        String extension = mimeType.getExtension();
        return minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(minioProperty.getBucket())
                .object(objectKeyPrefixEnum.getPrefix() + detect + '/'
                        + StringTools.getFileMd5(tempFile.toFile())+extension)
                .filename(tempFile.toAbsolutePath().toString())
                .build());
    }
}
