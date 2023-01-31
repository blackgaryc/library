package com.blackgaryc.library.core.register;


import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;

public class RegisterAutoConfiguration {

    @Bean
    UserRegisterFactory userRegisterFactory(){
        return new UserRegisterFactory();
    }

    @Bean
    public PasswordEncryptor passwordEncryptor() {
        return new StrongPasswordEncryptor();
    }
}
