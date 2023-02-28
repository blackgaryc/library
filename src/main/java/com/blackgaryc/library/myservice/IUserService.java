package com.blackgaryc.library.myservice;

import com.blackgaryc.library.core.login.IUserLoginService;

public interface IUserService extends IUserLoginService {
    boolean existAccountOrNot(String account);
}
