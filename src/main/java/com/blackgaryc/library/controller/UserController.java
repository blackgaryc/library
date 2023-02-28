package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.error.*;
import com.blackgaryc.library.core.login.IUserLoginService;
import com.blackgaryc.library.domain.user.LoginRequest;
import com.blackgaryc.library.domain.user.RegisterRequest;
import com.blackgaryc.library.domain.user.UpdateUserInfoRequest;
import com.blackgaryc.library.domain.user.UserInfoResponse;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.service.UserService;
import com.blackgaryc.library.myservice.impl.AbstractUserRegisterService;
import com.blackgaryc.library.core.register.EmailVerificationStrategy;
import com.blackgaryc.library.core.register.RegisterTypeEnum;
import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.tools.MinioSTSTools;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    public BaseResult getUserInfo() {
        long loginId = StpUtil.getLoginIdAsLong();
        UserEntity userEntity = userService.getBaseMapper().selectById(loginId);
        return Results.successData(new UserInfoResponse(userEntity));
    }

    @PostMapping("/info/update")
    public BaseResult updateUserInfo(@RequestBody UpdateUserInfoRequest request) {
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
     *
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
     */
    @PostMapping("register")
    @SaIgnore
    public BaseResult register(@RequestBody RegisterRequest form) throws LibraryException {
        String key = "register_new_user_times_in_day:" + form.getAccount();
        String registerTimes = stringRedisTemplate.opsForValue().get(key);
        if (registerTimes == null) {
            stringRedisTemplate.opsForValue().set(key, "0", 24, TimeUnit.HOURS);
        }
        Long times4user = stringRedisTemplate.opsForValue().increment(key);
        if (null != times4user && times4user > 6) {
            throw new LibraryException("今日您已经失败太多次，请明日再来注册");
        }
        try {
            RegisterTypeEnum registerTypeEnum = RegisterTypeEnum.valueOf(form.getType());
            AbstractUserRegisterService service = userRegisterFactory.getService(registerTypeEnum);
            boolean userRegistered = service.isUserRegistered(form.getAccount());
            if (userRegistered) {
                throw new RegisterUserAlreadyExistException(form.getAccount());
            }
            emailVerificationStrategy.check(form.getAccount(), form.getCode());
            //user service to create user;
            Long uid = service.registerUser(form.getAccount(), form.getPassword());
            //sa login as new user
            StpUtil.login(uid);
            return Results.successData(StpUtil.getTokenInfo());
        } catch (IllegalArgumentException e) {
            throw new RegisterTypeNotFoundException(form.getType());
        }
    }

    /**
     * @param account email to send verification code
     */
    @GetMapping("register/vcode/send")
    @SaIgnore
    public BaseResult sendVerificationCode(String account) throws LibraryException {
        String key = "email_send_time_limit:" + StpUtil.getTokenValue();
        String s = stringRedisTemplate.opsForValue().get(key);
        if (Objects.isNull(s)) {
            stringRedisTemplate.opsForValue().set(key, "1", 60, TimeUnit.MICROSECONDS);
        } else {
            throw new LibraryException("请求过于频繁，请稍后再试");
        }
        emailVerificationStrategy.sendTo(account);
        return Results.success();
    }

    @GetMapping("register/account/check")
    @SaIgnore
    public BaseResult checkAccount(@RequestParam(defaultValue = "") String account) {
        Long count = userService.lambdaQuery()
                .eq(UserEntity::getEmail, account)
                .or()
                .eq(UserEntity::getAccount, account)
                .count();
        if (count > 0) {
            return Results.errorMessage("该账户已经被注册或已经被绑定");
        }
        return Results.success();
    }

//    @GetMapping("register/vcode/check")
//    @SaIgnore
//    public BaseResult checkVerificationCode(@RequestParam(defaultValue = "") String code, String account) throws VerificationCodeException {
//        emailVerificationStrategy.check(account,code);
//        return Results.success();
//    }

    @Resource
    StringRedisTemplate stringRedisTemplate;

    private final EmailVerificationStrategy emailVerificationStrategy;
    private final UserRegisterFactory userRegisterFactory;

    public UserController(IUserLoginService userLoginService, MinioSTSTools minioSTSTools, EmailVerificationStrategy emailVerificationStrategy, UserRegisterFactory userRegisterFactory) {
        this.userLoginService = userLoginService;
        this.minioSTSTools = minioSTSTools;
        this.emailVerificationStrategy = emailVerificationStrategy;
        this.userRegisterFactory = userRegisterFactory;
    }
}
