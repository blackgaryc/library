package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.error.*;
import com.blackgaryc.library.core.login.IUserLoginService;
import com.blackgaryc.library.domain.user.LoginRequest;
import com.blackgaryc.library.domain.user.UpdateUserInfoRequest;
import com.blackgaryc.library.domain.user.UserInfoResponse;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.service.UserService;
import com.blackgaryc.library.service.impl.AbstractUserRegisterService;
import com.blackgaryc.library.core.register.EmailVerificationStrategy;
import com.blackgaryc.library.core.register.RegisterTypeEnum;
import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.tools.MinioSTSTools;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@SaCheckLogin
@RequestMapping("/user")
public class UserController {


    private final IUserLoginService userLoginService;

    @Autowired
    MinioClient minioClient;

    /**
     * login api, according to provided account and password,
     * check account is exist or not, and check password with encoded password;
     *
     * @param form user account and password
     * @return base result with success info
     * @throws LoginException if raise any LoginException, it will be processed at global handler.
     */
    @PostMapping("login")
    @SaIgnore
    public BaseResult doLogin(@RequestBody LoginRequest form) throws LoginException {
        UserEntity check = userLoginService.check(form.getAccount(), form.getPassword());
        StpUtil.login(check.getId());
        return Results.success();
    }

    @Resource
    UserService userService;
    @GetMapping("info")
    public BaseResult getUserInfo(){
        long loginId = StpUtil.getLoginIdAsLong();
        UserEntity userEntity = userService.getBaseMapper().selectById(loginId);
        return Results.successData(new UserInfoResponse(userEntity));
    }

    @PostMapping("/info/update")
    public BaseResult updateUserInfo(@RequestBody UpdateUserInfoRequest request){
        UserEntity userEntity = userService.getBaseMapper().selectById(StpUtil.getLoginIdAsLong());
        userEntity.setNickname(request.getNickname());
        userEntity.setAvatar(request.getAvatar());
        userService.updateById(userEntity);
        return Results.success();
    }


    private final MinioSTSTools minioSTSTools;

    /***
     * query login status
     * @return
     */
    @GetMapping("login/info")
    public BaseResult userInfo() {
        return Results.successData(StpUtil.getTokenInfo());
    }


    /**
     * get user minio tmp key and secret
     * @return
     */
    @GetMapping("sts/generate")
    public BaseResult userSts() {
        MinioSTSTools.CredentialsResult secureToken = minioSTSTools.createSecureToken(StpUtil.getLoginIdAsString());
        return Results.successData(secureToken);
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
            if (userRegistered) {
                throw new RegisterUserAlreadyExistException(account);
            }
            emailVerificationStrategy.check(account, vCode);
            //user service to create user;
            Long uid = service.registerUser(account, password);
            //sa login as new user
            StpUtil.login(uid);
            return Results.successData(StpUtil.getTokenInfo());
        } catch (IllegalArgumentException e) {
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
