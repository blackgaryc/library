package com.blackgaryc.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LibraryApplication {
    public static String PREFIX="SPRING_LIBRARY_";
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
