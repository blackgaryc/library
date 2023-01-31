package com.blackgaryc.library.core.register;

/**
 * interface to save verification code.
 */
public interface VerificationCodeService extends VerificationCodeCheckerService{
    void save(String user,String code);
}
