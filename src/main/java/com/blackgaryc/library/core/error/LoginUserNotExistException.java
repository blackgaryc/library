package com.blackgaryc.library.core.error;

public class LoginUserNotExistException extends LoginException{
    public LoginUserNotExistException(String account) {
        super("用户"+account+"不存在");
    }
}
