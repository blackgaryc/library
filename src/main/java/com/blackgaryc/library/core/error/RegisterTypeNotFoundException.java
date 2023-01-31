package com.blackgaryc.library.core.error;

public class RegisterTypeNotFoundException extends RegisterException {
    public RegisterTypeNotFoundException(String type) {
        super("暂不支持[" + type + "]登陆");
    }
}
