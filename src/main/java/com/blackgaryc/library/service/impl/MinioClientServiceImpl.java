package com.blackgaryc.library.service.impl;

import com.blackgaryc.library.core.minio.IObjectKey;
import com.blackgaryc.library.core.minio.MinioProperty;
import com.blackgaryc.library.service.MinioClientService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioClientServiceImpl implements MinioClientService {
    @Resource
    MinioProperty minioProperty;
    @Resource
    MinioClient minioClient;
    @Resource
    Tika tika;

    @Override
    public String getUploadSignedUrl(String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .expiry(600)
                .method(Method.PUT)
                .bucket(minioProperty.getBucket())
                .object(path)
                .build());
    }

    @Override
    public ObjectWriteResponse uploadObject(File file, String objectKey) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String mimetype = tika.detect(file);
        return minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(minioProperty.getBucket())
                .object(objectKey)
                .contentType(mimetype)
                .filename(file.getAbsolutePath())
                .build());
    }


    @Override
    public ObjectWriteResponse uploadObject(File file, IObjectKey objectKey) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return this.uploadObject(file, objectKey.getKey());
    }

    @Override
    public String getFullUrl(String objectKey) {
        return minioProperty.getEndpoint()+'/'+minioProperty.getBucket()+'/'+objectKey;
    }

    @Override
    public String getUploadSignedUrl(IObjectKey objectKey) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return getUploadSignedUrl(objectKey.getKey());
    }

    @Override
    public Iterable<Result<Item>> listObjects(String object) {
        return minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(minioProperty.getBucket())
                        .prefix(object)
                        .build());
    }
}
