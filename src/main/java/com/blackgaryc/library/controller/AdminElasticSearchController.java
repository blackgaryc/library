package com.blackgaryc.library.controller;

import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.core.elasticsearch.repository.BookRepository;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.myservice.AdminBookSearchService;
import com.blackgaryc.library.service.BookService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/admin/manage/elasticsearch")
@RestController
public class AdminElasticSearchController {
    @Resource
    BookRepository bookRepository;
    @Resource
    AdminBookSearchService adminBookSearchService;
    @Resource
    BookService bookService;
    @Resource
    ElasticsearchOperations elasticsearchOperations;

    //更新数据库中所有的数据
    @GetMapping("update/all")
    public BaseResult test() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        long now = System.currentTimeMillis();
        Page<BookEntity> page = bookService.page(Page.of(1, 20));
        List<BookEntity> records = page.getRecords();
        List<Book> data1 = adminBookSearchService.getBook4ElasticSearchByBookEntities(records);
        bookRepository.saveAll(data1);
        for (int i = 2; i <= page.getPages(); i++) {
            Page<BookEntity> page1 = bookService.page(Page.of(i, page.getSize()));
            List<Book> book4ElasticSearchByBookEntities = adminBookSearchService.getBook4ElasticSearchByBookEntities(page1.getRecords());
            bookRepository.saveAll(book4ElasticSearchByBookEntities);
        }
        return Results.successMessage((System.currentTimeMillis() - now) + "更新" + page.getTotal() + "条数据");
    }

    //更新单独一本书的数据
    @GetMapping("update/{id}")
    public BaseResult updateById(@PathVariable Long id) {
        BookEntity byId = bookService.getById(id);
        Book entity = adminBookSearchService.getBook4ElasticSearchByBookEntity(byId);
        bookRepository.save(entity);
        return Results.success();
    }
}
