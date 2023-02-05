package com.blackgaryc.library.service.impl;

import com.blackgaryc.library.core.register.RegisterTypeEnum;
import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.mapper.UserMapper;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
public class EmailUserRegisterStrategy extends AbstractUserRegisterService {
    public EmailUserRegisterStrategy(PasswordEncryptor passwordEncryptor,UserMapper userMapper, UserRegisterFactory userRegisterFactory) {
        super(passwordEncryptor, userMapper, userRegisterFactory);
    }

    /**
     * process to register user by email
     *
     * @param user     account
     * @param password password
     * @return new user uid
     */
    @Override
    public Long registerUser(String user, String password) {
        //save user
        UserEntity entity = new UserEntity();
        entity.setEmail(user);
        entity.setAccount(user);
        entity.setPassword(password);
        super.registerUser(entity);
        //save role
        //save permissions
        return entity.getId();
    }

    @Override
    public RegisterTypeEnum getType() {
        return RegisterTypeEnum.EMAIL;
    }
}
