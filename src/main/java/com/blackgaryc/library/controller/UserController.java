package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.error.*;
import com.blackgaryc.library.core.login.IUserLoginService;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.service.impl.AbstractUserRegisterService;
import com.blackgaryc.library.core.register.EmailVerificationStrategy;
import com.blackgaryc.library.core.register.RegisterTypeEnum;
import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.tools.MinioSTSTools;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@SaCheckLogin
@RequestMapping("/user/")
public class UserController {


    private final IUserLoginService userLoginService;

    @Autowired
    MinioClient minioClient;

    /**
     * login api, according to provided account and password,
     * check account is exist or not, and check password with encoded password;
     * @param account user account
     * @param password user password
     * @return base result with success info
     * @throws LoginException if raise any LoginException, it will be processed at global handler.
     */
    @GetMapping("login")
    @SaIgnore
    public BaseResult doLogin(String account,String password) throws LoginException {
        UserEntity check = userLoginService.check(account, password);
        StpUtil.login(check.getId());
        return Results.success();
    }


    private final MinioSTSTools minioSTSTools;
    @GetMapping("login/info")
    public BaseResult userInfo() {
        MinioSTSTools.CredentialsResult secureToken = minioSTSTools.createSecureToken(StpUtil.getLoginIdAsString());
        HashMap<String, Object> result= new HashMap<>();
        result.put("token",StpUtil.getTokenInfo());
        result.put("secureToken",secureToken);
        return Results.successData(result);
    }

    /**
     * register new user <br>
     * first thing is to check verification code.<br>
     * then create new user and save to database
     *
     * @param account  user account
     * @param password user password
     * @param vCode    verification code
     */
    @PostMapping("register")
    @SaIgnore
    public BaseResult register(String account, String password, String vCode, String type) throws VerificationCodeException, RegisterException {
        try {
            RegisterTypeEnum registerTypeEnum = RegisterTypeEnum.valueOf(type);
            AbstractUserRegisterService service = userRegisterFactory.getService(registerTypeEnum);
            boolean userRegistered = service.isUserRegistered(account);
            if (userRegistered){
                throw new RegisterUserAlreadyExistException(account);
            }
            emailVerificationStrategy.check(account, vCode);
            //user service to create user;
            Long uid = service.registerUser(account, password);
            //sa login as new user
            StpUtil.login(uid);
            return Results.successData(StpUtil.getTokenInfo());
        }catch (IllegalArgumentException e){
            throw new RegisterTypeNotFoundException(type);
        }
    }

    /**
     * now, it's able to send email but hasn't any protect to prevent illegal call
     *
     * @param user email to send verification code
     */
    @GetMapping("register/verification_code")
    @SaIgnore
    public void registerVerificationCode(String user) {
        emailVerificationStrategy.sendTo(user);
    }

    private final EmailVerificationStrategy emailVerificationStrategy;
    private final UserRegisterFactory userRegisterFactory;
    public UserController(IUserLoginService userLoginService, MinioSTSTools minioSTSTools, EmailVerificationStrategy emailVerificationStrategy, UserRegisterFactory userRegisterFactory) {
        this.userLoginService = userLoginService;
        this.minioSTSTools = minioSTSTools;
        this.emailVerificationStrategy = emailVerificationStrategy;
        this.userRegisterFactory = userRegisterFactory;
    }
}
