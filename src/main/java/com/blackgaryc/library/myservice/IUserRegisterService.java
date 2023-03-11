package com.blackgaryc.library.myservice;


import com.blackgaryc.library.myservice.impl.AbstractUserRegisterService;
import com.blackgaryc.library.core.register.RegisterTypeEnum;

/**
 * interface to register new user, u needn't to implements
 * this inter face, you should try to extend abstract class {@link AbstractUserRegisterService }
 */
public interface IUserRegisterService {

    /**
     * check account is registered or not
     *
     * @param user account, may be email or phone
     * @return if account has registered, will return true, else false
     */
    boolean isUserRegistered(String user);

    /**
     * register new user
     *
     * @param user     account
     * @param password password
     * @param type
     * @return userId
     */
    Long registerUser(String user, String password,String type);

    RegisterTypeEnum getType();
}
