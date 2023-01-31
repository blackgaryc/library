package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.minio.MinioAutoConfiguration;
import com.blackgaryc.library.core.minio.MinioProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class TestController {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MinioProperty config;
    @Autowired
    MinioClient minioClient;
    @GetMapping("test")
    public String test(String text) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean b = minioClient.bucketExists(BucketExistsArgs.builder().bucket(config.getBucket()).build());
        if (b){
            System.out.println("exist bucket "+config.getBucket());
        }
        return objectMapper.valueToTree(config).toPrettyString();
    }
}
//AGWNNXIHBPCYWLQP