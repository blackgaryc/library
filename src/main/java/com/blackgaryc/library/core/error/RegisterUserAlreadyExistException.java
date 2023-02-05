package com.blackgaryc.library.core.error;

public class RegisterUserAlreadyExistException extends RegisterException {
    public RegisterUserAlreadyExistException(String user) {
        super("您注册的账户[" + user + "]已被注册");
    }
}
