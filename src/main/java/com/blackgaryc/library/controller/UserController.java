package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.error.VerificationCodeException;
import com.blackgaryc.library.core.register.AbstractUserRegisterService;
import com.blackgaryc.library.core.register.EmailVerificationStrategy;
import com.blackgaryc.library.core.register.RegisterTypeEnum;
import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @GetMapping("login")
    public Object doLogin() {
        StpUtil.login(10001);
        return "";
    }

    @SaCheckLogin
    @GetMapping("login/info")
    public BaseResult userInfo() {
        return Results.successData(StpUtil.getTokenInfo());
    }

    private final EmailVerificationStrategy emailVerificationStrategy;

    public UserController(EmailVerificationStrategy emailVerificationStrategy, UserRegisterFactory userRegisterFactory) {
        this.emailVerificationStrategy = emailVerificationStrategy;
        this.userRegisterFactory = userRegisterFactory;
    }


    private final UserRegisterFactory userRegisterFactory;

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
    public BaseResult register(String account, String password, String vCode, String type) throws VerificationCodeException {
        emailVerificationStrategy.check(account, vCode);
        //user service to create user;
        RegisterTypeEnum registerTypeEnum = RegisterTypeEnum.valueOf(type);
        AbstractUserRegisterService service = userRegisterFactory.getService(registerTypeEnum);
        Long uid = service.registerUser(account, password);
        //sa login as new user
        StpUtil.login(uid);
        return Results.successData(StpUtil.getTokenInfo());
    }

    /**
     * now, it's able to send email but hasn't any protect to prevent illegal call
     *
     * @param user email to send verification code
     */
    @GetMapping("register/verification_code")
    public void registerVerificationCode(String user) {
        emailVerificationStrategy.sendTo(user);
    }
}
