package com.blackgaryc.library.core.login;

import com.blackgaryc.library.core.error.LoginException;
import com.blackgaryc.library.entity.UserEntity;

public interface IUserLoginService {
    UserEntity check(String account, String password) throws LoginException;
}
