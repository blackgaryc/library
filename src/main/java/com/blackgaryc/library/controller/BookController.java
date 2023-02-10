package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blackgaryc.library.domain.book.CreateBookRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SaCheckLogin
@RequestMapping("/book")
@RestController
public class BookController {
    //create book by minio filepath
    @PostMapping("/create")
    public void createBookByMinioFile(CreateBookRequest form){

    }
}
