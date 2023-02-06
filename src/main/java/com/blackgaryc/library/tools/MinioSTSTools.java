package com.blackgaryc.library.tools;

import com.blackgaryc.library.core.minio.MinioProperty;
import io.minio.credentials.AssumeRoleProvider;
import io.minio.credentials.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;


/**
 * create accessKey and secretKey for web user to upload file direct
 */
@Component
public class MinioSTSTools {
    private final Logger log = LoggerFactory.getLogger(MinioSTSTools.class);
    private final MinioProperty minioProperty;

    private final String ACCESS_KEY_COMPANY = "libraryuser";
    private final String SECRET_KEY_COMPANY = "libraryuser";

    public static final String REGION = null;
    //上传的bucket名
    public static final String BUCKET = "test";
    //授权策略，允许访问名为bucket的桶的目录
    public static final String ROLE_SESSION_NAME = "anysession";
    //定义策略，可进行二次限定


    public MinioSTSTools(MinioProperty minioProperty) {
        this.minioProperty = minioProperty;
    }

    public CredentialsResult createSecureToken(String uid) {
        assert null != uid && !uid.isEmpty() && !uid.isBlank();
        int durationSeconds = 3600 * 10;
        String ROLE_ARN = "arn:aws:s3:::library/user/" + uid;
        String POLICY = "{\n" +
                " \"Version\": \"2012-10-17\",\n" +
                " \"Statement\": [\n" +
                "  {\n" +
                "   \"Effect\": \"Allow\",\n" +
                "   \"Action\": [\n" +
                "    \"s3:GetObject\",\n" +
                "    \"s3:PutObject\"\n" +
                "   ],\n" +
                "   \"Resource\": [\n" +
                "    \"arn:aws:s3:::library/user/" + uid + "/*\"\n" +
                "   ]\n" +
                "  },\n" +
                " {\n" +
                "   \"Effect\": \"Allow\",\n" +
                "   \"Action\": [\n" +
                "    \"s3:GetBucketLocation\"\n" +
                "   ],\n" +
                "   \"Resource\": [\n" +
                "    \"arn:aws:s3:::library\"\n" +
                "   ]\n" +
                "  }" +
                " ]\n" +
                "}";
        log.info("POLICY = " + POLICY);
        try {
            AssumeRoleProvider provider = new AssumeRoleProvider(minioProperty.getEndpoint(), ACCESS_KEY_COMPANY,
                    SECRET_KEY_COMPANY, durationSeconds,
                    POLICY,
                    REGION,
                    ROLE_ARN,
                    ROLE_SESSION_NAME,
                    null,
                    null);
            Date expirationDate = Date.from(Instant.now().plusSeconds(durationSeconds));
            /**
             * 打印provider签名属性
             */
            log.info("sessionToken=" + provider.fetch().sessionToken());
            log.info("accessKey=" + provider.fetch().accessKey());
            log.info("secretKey=" + provider.fetch().secretKey());
            log.info("isExpired=" + provider.fetch().isExpired());
            log.info("expirationDate=" + expirationDate);
            return new CredentialsResult(provider.fetch());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static class CredentialsResult {
        private final Credentials credentials;

        public CredentialsResult(Credentials credentials) {
            this.credentials = credentials;
        }

        public String getAccessKey() {
            return credentials.accessKey();
        }

        public String getSecretKey() {
            return credentials.secretKey();
        }

        public String getSessionToken() {
            return credentials.sessionToken();

        }
    }
}
