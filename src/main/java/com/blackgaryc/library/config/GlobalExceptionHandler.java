package com.blackgaryc.library.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public BaseResult handlerException(NotPermissionException e) {
        return Results.errorMessage(e.getMessage());
    }

    @ExceptionHandler
    public BaseResult handlerException(NotLoginException e){
        return Results.errorMessage(e.getMessage());
    }

    @ExceptionHandler
    public BaseResult handlerException(LibraryException e){
        return Results.errorMessage(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult handleException(SizeLimitExceededException e){
        return Results.errorMessage("filesize can't bigger than 30MB");
    }
}
