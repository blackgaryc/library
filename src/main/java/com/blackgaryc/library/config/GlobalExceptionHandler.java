package com.blackgaryc.library.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public BaseResult handlerException(NotPermissionException e) {
        System.out.println("permission = " + e.getPermission());
        System.out.println("login type = " + e.getLoginType());
        System.out.println("message = " + e.getMessage());
        e.printStackTrace();
        return Results.errorMessage(e.getMessage());
    }

    @ExceptionHandler
    public BaseResult handlerException(NotLoginException e){
        System.out.println("login type = " + e.getLoginType());
        System.out.println("message = " + e.getMessage());
        e.printStackTrace();
        return Results.errorMessage(e.getMessage());
    }

    @ExceptionHandler
    public BaseResult handlerException(LibraryException e){
        return Results.errorMessage(e.getMessage());
    }
}
