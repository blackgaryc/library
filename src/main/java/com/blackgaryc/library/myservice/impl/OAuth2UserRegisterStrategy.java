package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.core.register.RegisterTypeEnum;
import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.entity.UserEntity;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
public class OAuth2UserRegisterStrategy extends AbstractUserRegisterService{

    public OAuth2UserRegisterStrategy(PasswordEncryptor passwordEncryptor, UserRegisterFactory userRegisterFactory) {
        super(passwordEncryptor, userRegisterFactory);
    }

    @Override
    public Long registerUser(String id, String password, String providerId) {
        UserEntity userEntity = new UserEntity();
        //TODO 需要优化
        if (providerId.equals("github")){
            userEntity.setGithubId(id);
            userEntity.setAccount(providerId+id);
        }else {
            throw new RuntimeException("unable to find oauth2 provider id[" + providerId + "]");
        }
        registerUser(userEntity);
        return userEntity.getId();
    }

    @Override
    public RegisterTypeEnum getType() {
        return RegisterTypeEnum.OAUTH2;
    }

    @Override
    public boolean isUserRegistered(String githubUid) {
        //待优化
        return super.userService.lambdaQuery().eq(UserEntity::getGithubId,githubUid).exists();
    }
}
