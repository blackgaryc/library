package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.core.elasticsearch.repository.BookRepository;
import com.blackgaryc.library.core.result.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SaCheckLogin
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @GetMapping("search")
    @SaIgnore
    public PageableResult searchBookByTitleOrMd5(String data,
                                                 @RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "50") Integer size){
        Page<Book> pageResult = bookRepository.findByTitleOrMd5(data,PageRequest.of(page,size));
        return new PageableResult<>(pageResult);
    }
}

