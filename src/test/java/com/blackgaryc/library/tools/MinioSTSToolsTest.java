package com.blackgaryc.library.tools;

import com.blackgaryc.library.core.minio.MinioProperty;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import io.minio.credentials.Credentials;
import io.minio.credentials.StaticProvider;
import io.minio.errors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MinioSTSToolsTest {
    @Autowired
    MinioSTSTools minioSTSTools;
    @Autowired
    MinioProperty minioProperty;
    MinioClient client;

    /**
     * upload file to namespace of uid 10
     * it should be failed
     * @throws IOException
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    @Test
    void createSecureTokenUpload2OtherUserNamespace() throws IOException, ServerException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Exception exception = assertThrows(ErrorResponseException.class, () -> {
            ObjectWriteResponse objectWriteResponse = client.uploadObject(UploadObjectArgs.builder().bucket(minioProperty.getBucket()).object("user/10/test").filename("/home/alex/Documents/Project/IdeaProjects/GraduationDesign/library/src/main/resources/application.properties").build());
            System.out.println("objectWriteResponse.etag() = " + objectWriteResponse.etag());
        });
        System.out.println("exception.getMessage() = " + exception.getMessage());
    }

    /**
     * upload file to namespace of uid 100
     * it should be success
     * @throws IOException
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     * @throws ErrorResponseException
     */
    @Test
    void createSecureTokenUpload2SelfNamespace() throws IOException, ServerException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ErrorResponseException {
        ObjectWriteResponse objectWriteResponse = client.uploadObject(UploadObjectArgs.builder().bucket(minioProperty.getBucket()).object("user/100/test").filename("/home/alex/Documents/Project/IdeaProjects/GraduationDesign/library/src/main/resources/application.properties").build());
        System.out.println("objectWriteResponse.etag() = " + objectWriteResponse.etag());
    }

    /**
     * generate secure token,and create minio client
     */
    @BeforeEach
    void setUp() {
        MinioSTSTools.CredentialsResult secureToken = minioSTSTools.createSecureToken("100");
        String accessKey = secureToken.getAccessKey();
        String secretKey = secureToken.getSecretKey();
        String sessionToken = secureToken.getSessionToken();
        StaticProvider staticProvider = new StaticProvider(accessKey, secretKey, sessionToken);
        this.client = new MinioClient.Builder().endpoint(minioProperty.getEndpoint()).credentialsProvider(staticProvider).build();
    }
}