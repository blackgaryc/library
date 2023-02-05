package com.blackgaryc.library.core.error;

public class LoginErrorPasswordException extends LoginException{
    public LoginErrorPasswordException() {
        super("帐号密码不正确");
    }
}
