package com.blackgaryc.library.core.register;

import com.blackgaryc.library.mapper.UserMapper;
import com.blackgaryc.library.myservice.impl.AbstractUserRegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class UserRegisterFactoryTest {

    @Autowired
    UserRegisterFactory factory;
    @Autowired
    UserMapper userMapper;
    @Test
    void getService() {
        AbstractUserRegisterService service = factory.getService(RegisterTypeEnum.EMAIL);
        Assert.notNull(service,"unable to load email register service");
    }

    @BeforeEach
    void setUp() {

    }
}