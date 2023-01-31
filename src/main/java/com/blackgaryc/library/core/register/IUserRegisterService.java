package com.blackgaryc.library.core.register;


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
     * @return userId
     */
    Long registerUser(String user, String password);

    RegisterTypeEnum getType();
}
