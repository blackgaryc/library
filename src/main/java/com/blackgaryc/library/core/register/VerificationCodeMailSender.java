package com.blackgaryc.library.core.register;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class VerificationCodeMailSender {
    private final JavaMailSender javaMailSender;

    public VerificationCodeMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    void sendCode(String mailTo, String code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailTo);
        msg.setFrom("blackgaryc@163.com");
        msg.setSubject("verification code");
        msg.setText("your verification code is : " + code);
        javaMailSender.send(msg);
    }
}
