package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.core.register.RegisterTypeEnum;
import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.entity.UserEntity;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
public class EmailUserRegisterStrategy extends AbstractUserRegisterService {
    public EmailUserRegisterStrategy(PasswordEncryptor passwordEncryptor, UserRegisterFactory userRegisterFactory) {
        super(passwordEncryptor, userRegisterFactory);
    }

    /**
     * process to register user by email
     *
     * @param user     account
     * @param password password
     * @param type
     * @return new user uid
     */
    @Override
    public Long registerUser(String user, String password, String type) {
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
