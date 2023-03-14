package com.blackgaryc.library;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableRabbit
@EnableCaching
public class LibraryApplication {
    public static String PREFIX="SPRING_LIBRARY_";
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
