package com.blackgaryc.library.core.elasticsearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.blackgaryc.library.core.elasticsearch.repository")
//@ComponentScan(basePackages = { "com.blackgaryc.library.core.elasticsearch.domain" })
//public class ElasticsearchConfig extends ElasticsearchConfiguration {
//
//    @Bean
//    @Override
//    public ClientConfiguration clientConfiguration() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("192.168.144.102:9200")
//                .build();
//
//        return clientConfiguration;
//    }
//}