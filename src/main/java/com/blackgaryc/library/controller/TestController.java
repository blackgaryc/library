package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.core.elasticsearch.repository.BookRepository;
import com.blackgaryc.library.core.minio.MinioProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import io.minio.errors.*;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class TestController {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MinioProperty config;
    @Autowired
    MinioClient minioClient;
    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    ElasticsearchClient elasticsearchClient;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    RestClient restClient;
    @GetMapping("test")
    public List<Book> test(String text) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Book entity = new Book();
        entity.setTitle("test save book");
        Book save = operations.save(entity);

//        PageRequest pageable = PageRequest.of(1,10).withPage(1);
//        Page<Book> test = bookRepository.findBooksByTitle("test", pageable);
//        return test.getContent();
        return null;
    }
}