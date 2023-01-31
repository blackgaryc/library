package com.blackgaryc.library.core.register;

import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.error.VerificationCodeException;

public interface VerificationCodeCheckerService {
    boolean check(String user,String code) throws VerificationCodeException;
}
