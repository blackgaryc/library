package com.blackgaryc.library.core.minio;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class MinioAutoConfiguration {
    /**
     * read properties
     */
    @Import(MinioProperty.class)
    @Configuration
    protected static class MinioPropertyAutoConfiguration {

    }

    /**
     * create client
     *
     * @param minioProperty
     * @return
     */
    @Bean
    MinioClient minioClient(MinioProperty minioProperty) {
        return MinioClient.builder().endpoint(minioProperty.getEndpoint()).credentials(minioProperty.getAccessKey(), minioProperty.getSecretKey()).build();
    }
}
