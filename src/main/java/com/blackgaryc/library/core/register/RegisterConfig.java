package com.blackgaryc.library.core.register;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterConfig {
    @ConditionalOnMissingBean(VerificationCodeService.class)
    @Bean
    public VerificationCodeService verificationCodeService(){
        return new DefaultVerificationCodeService();
    }
}
