package com.blackgaryc.library.core.register;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EmailVerificationStrategy extends AbstractVerificationCodeStrategy implements InitializingBean {
    private final VerificationCodeMailSender mailSender;

    public EmailVerificationStrategy(ApplicationContext applicationContext, VerificationCodeMailSender mailSender,VerificationCodeService verificationCodeService) {
        super(applicationContext);
        this.mailSender = mailSender;
    }

    @Override
    protected void handleSendMessage(String user, String code) {
        mailSender.sendCode(user, code);
    }

}
