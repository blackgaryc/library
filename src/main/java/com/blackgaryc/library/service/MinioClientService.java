package com.blackgaryc.library.service;

import com.blackgaryc.library.core.minio.IObjectKey;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ObjectWriteResponse;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface MinioClientService {
    String getUploadSignedUrl(String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    String getUploadSignedUrl(IObjectKey objectKey) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    ObjectWriteResponse uploadObject(File file, String objectKey) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    ObjectWriteResponse uploadObject(File file, IObjectKey objectKey) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    String getFullUrl(String objectKey);
    Iterable<Result<Item>> listObjects(String object);
    String getPresignedUrl(String object, Method method, Map<String,String> extraHeader,int expiry) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
    String getPresignedUrl(GetPresignedObjectUrlArgs.Builder builder) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
