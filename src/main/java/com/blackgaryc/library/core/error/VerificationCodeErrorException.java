package com.blackgaryc.library.core.error;

public class VerificationCodeErrorException extends VerificationCodeException {
    public VerificationCodeErrorException() {
        super("验证码错误");
    }
}
