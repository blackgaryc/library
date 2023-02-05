package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.core.error.LoginErrorPasswordException;
import com.blackgaryc.library.core.error.LoginException;
import com.blackgaryc.library.core.error.LoginUserNotExistException;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.core.login.IUserLoginService;
import com.blackgaryc.library.service.UserService;
import com.blackgaryc.library.mapper.UserMapper;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-01-27 12:35:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserService, IUserLoginService {
    private final PasswordEncryptor passwordEncryptor;

    public UserServiceImpl(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public UserEntity check(String account, String password) throws LoginException {
        boolean existUserByAccount = baseMapper.existUserByAccount(account);
        if (!existUserByAccount){
            throw new LoginUserNotExistException(account);
        }
        UserEntity userEntity = baseMapper.selectByAccount(account);
        boolean equals = passwordEncryptor.checkPassword(password, userEntity.getPassword());
        if (!equals){
            throw new LoginErrorPasswordException();
        }
        return userEntity;
    }
}




