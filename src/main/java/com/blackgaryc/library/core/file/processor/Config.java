package com.blackgaryc.library.core.file.processor;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    Tika tika(){
        return new Tika();
    }
}
