package com.blackgaryc.library.core.register;

import com.blackgaryc.library.core.error.VerificationCodeException;

public interface VerificationCodeStrategy {
    void sendTo(String user);

    void check(String user, String code) throws VerificationCodeException;
}
