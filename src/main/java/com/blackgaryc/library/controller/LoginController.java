package com.blackgaryc.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("login/oauth2/{type}")
    public void login(@PathVariable String type){

        System.out.println("type = " + type);
    }
}
