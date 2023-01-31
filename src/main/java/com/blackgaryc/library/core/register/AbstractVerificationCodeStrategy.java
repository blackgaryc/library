package com.blackgaryc.library.core.register;

import com.blackgaryc.library.core.error.VerificationCodeException;
import com.blackgaryc.library.tools.RandStringTools;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import java.util.Objects;

public abstract class AbstractVerificationCodeStrategy implements VerificationCodeStrategy, InitializingBean {
    private VerificationCodeService verificationCodeService;

    private final ApplicationContext applicationContext;

    public AbstractVerificationCodeStrategy(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    AbstractVerificationCodeStrategy(VerificationCodeService verificationCodeService, ApplicationContext applicationContext) {
        this.verificationCodeService = verificationCodeService;
        this.applicationContext = applicationContext;
    }

    @Override
    public void sendTo(String user) {
        //generate code
        String code = RandStringTools.randNumberString(6);
        //save user
        verificationCodeService.save(user, code);
        // handle how to send
        handleSendMessage(user, code);
    }

    @Override
    public void check(String user, String code) throws VerificationCodeException {
        verificationCodeService.check(user, code);
    }

    protected abstract void handleSendMessage(String user, String code);

    @Override
    public void afterPropertiesSet() throws Exception {
        this.verificationCodeService = Objects.requireNonNullElseGet(verificationCodeService, DefaultVerificationCodeService::new);
    }
}
