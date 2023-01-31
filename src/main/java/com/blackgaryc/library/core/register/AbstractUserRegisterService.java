package com.blackgaryc.library.core.register;

import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.mapper.UserMapper;
import com.blackgaryc.library.tools.RandStringTools;
import org.jasypt.util.password.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.Objects;

/**
 * abstract class to register new user
 */
public abstract class AbstractUserRegisterService implements IUserRegisterService, InitializingBean {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PasswordEncryptor passwordEncryptor;
    private final UserMapper userMapper;
    private final UserRegisterFactory userRegisterFactory;

    public AbstractUserRegisterService(PasswordEncryptor passwordEncryptor, UserMapper userMapper, UserRegisterFactory userRegisterFactory) {
        this.passwordEncryptor = passwordEncryptor;
        this.userMapper = userMapper;
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
        return userMapper.existUserByAccount(user);
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
            user.setNickname("用户-" + RandStringTools.randNumberString(4));
        }
        if (1 != userMapper.insert(user)) {
            throw new RuntimeException("unable to insert new user");
        }
    }
}
