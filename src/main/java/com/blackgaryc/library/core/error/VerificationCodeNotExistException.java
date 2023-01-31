package com.blackgaryc.library.core.error;

public class VerificationCodeNotExistException extends VerificationCodeException {
    public VerificationCodeNotExistException() {
        super("请先发送验证码");
    }
}
