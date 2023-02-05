package com.blackgaryc.library.tools;

import org.jasypt.util.password.PasswordEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordEncryptorTests {
    @Autowired
    PasswordEncryptor passwordEncryptor;
    @Test
    void testEncodePassword(){
        String passwd = "123456";
        String encodePasswd = passwordEncryptor.encryptPassword(passwd);
        assert passwordEncryptor.checkPassword(passwd,encodePasswd);
    }
}
