package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.myservice.IUserService;
import com.blackgaryc.library.myservice.IUserRegisterService;
import com.blackgaryc.library.service.UserService;
import com.blackgaryc.library.tools.StringTools;
import org.jasypt.util.password.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * abstract class to register new user
 */
public abstract class AbstractUserRegisterService implements IUserRegisterService, InitializingBean {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PasswordEncryptor passwordEncryptor;
    @Resource
    IUserService iUserService;
    @Resource
    UserService userService;
    private final UserRegisterFactory userRegisterFactory;

    public AbstractUserRegisterService(PasswordEncryptor passwordEncryptor, UserRegisterFactory userRegisterFactory) {
        this.passwordEncryptor = passwordEncryptor;
        this.userRegisterFactory = userRegisterFactory;
    }

    /**
     * check account has registered or not
     *
     * @param user account, may be email or phone
     * @return
     */
    @Override
    public boolean isUserRegistered(String user) {
        return iUserService.existAccountOrNot(user);
    }

    /**
     * auto save to factory
     *
     * @throws Exception if repeat maybe rise exception,but now hasn't supported to check.
     */
    @Override
    public final void afterPropertiesSet() throws Exception {
        userRegisterFactory.apply(getType(), this);
    }

    final void registerUser(UserEntity user) {
        String password = user.getPassword();
        if (null == password || password.isEmpty() || password.isBlank()) {
            log.warn("created user account[" + user.getAccount() + "] has empty password, default password is set to 123456");
            password = "123456";
        }
        user.setPassword(passwordEncryptor.encryptPassword(password));
        String nickname = user.getNickname();
        if (null == nickname || nickname.isEmpty() || nickname.isBlank()) {
            user.setNickname("用户-" + StringTools.randNumberString(4));
        }
        if (!userService.save(user)) {
            throw new RuntimeException("unable to insert new user");
        }
    }
}
