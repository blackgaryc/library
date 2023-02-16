package com.blackgaryc.library.controller;

import com.blackgaryc.library.LibraryApplication;
import com.blackgaryc.library.core.minio.MinioProperty;
import com.blackgaryc.library.core.minio.ObjectKeyPrefixEnum;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.tools.StringTools;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.apache.tika.Tika;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    Tika tika;
    @Resource
    MinioClient minioClient;
    @Resource
    MinioProperty minioProperty;

    @PostMapping("/upload")
    public BaseResult uploadFile(MultipartFile file,String type) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // type must be resolved , it contains object prefix which is important of upload file.
        ObjectKeyPrefixEnum objectKeyPrefixEnum = ObjectKeyPrefixEnum.valueOf(type);
        String originalFilename = file.getOriginalFilename();
        Path tempFile = Files.createTempFile(LibraryApplication.PREFIX, originalFilename);
        file.transferTo(tempFile);
        ObjectWriteResponse objectWriteResponse = uploadFile(objectKeyPrefixEnum, originalFilename, tempFile);
        tempFile.toFile().delete();
        String object = objectWriteResponse.object();
        return Results.successData(object);
    }

    private ObjectWriteResponse uploadFile(ObjectKeyPrefixEnum objectKeyPrefixEnum, String originalFilename, Path tempFile) throws IOException, ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException {
        String detect = tika.detect(tempFile);
        ObjectWriteResponse objectWriteResponse = minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(minioProperty.getBucket())
                .object(objectKeyPrefixEnum.getPrefix() + detect + '/'
                        + StringTools.getMd5AsFilenameWithOriginException(originalFilename, tempFile.toFile()))
                .filename(tempFile.toAbsolutePath().toString())
                .build());
        return objectWriteResponse;
    }
}
