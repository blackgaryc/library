package com.blackgaryc.library.core.error;

public class VerificationCodeExpressException extends VerificationCodeException {
    public VerificationCodeExpressException() {
        super("验证码超时");
    }
}
