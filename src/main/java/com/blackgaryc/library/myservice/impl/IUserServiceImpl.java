package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.core.error.LoginErrorPasswordException;
import com.blackgaryc.library.core.error.LoginException;
import com.blackgaryc.library.core.error.LoginUserNotExistException;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.myservice.IUserService;
import com.blackgaryc.library.service.UserService;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class IUserServiceImpl implements IUserService {
    @Resource
    UserService userService;

    @Override
    public boolean existAccountOrNot(String account) {
        return userService.lambdaQuery().eq(UserEntity::getAccount,account).exists();
    }
    @Resource
    PasswordEncryptor passwordEncryptor;
    @Override
    public UserEntity check(String account, String password) throws LoginException {
        UserEntity entity = userService.lambdaQuery().eq(UserEntity::getAccount,account).one();
        if (Objects.isNull(entity)){
            throw new LoginUserNotExistException(account);
        }
        if (!passwordEncryptor.checkPassword(password, entity.getPassword())){
            throw new LoginErrorPasswordException();
        }
        entity.setPassword("");
        return entity;
    }
}
