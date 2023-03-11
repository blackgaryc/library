package com.blackgaryc.library.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.error.RegisterUserAlreadyExistException;
import com.blackgaryc.library.core.oauth2.OAuth2Service;
import com.blackgaryc.library.core.register.RegisterTypeEnum;
import com.blackgaryc.library.core.register.UserRegisterFactory;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.myservice.IUserService;
import com.blackgaryc.library.myservice.impl.AbstractUserRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("login")
public class LoginController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    OAuth2Service oAuth2Service;
    @GetMapping("oauth2/authorization/{type}")
    public RedirectView login(@PathVariable String type) {
        String authRedirectUrl = oAuth2Service.getAuthRedirectUrl(type);
        log.info(authRedirectUrl);
        return new RedirectView(authRedirectUrl);
    }

    @Resource
    UserRegisterFactory userRegisterFactory;

    @Resource
    IUserService userService;

    @GetMapping("oauth2/code/{type}")
    public Object codeLogin(HttpServletRequest httpServletRequest, @PathVariable String type) throws RegisterUserAlreadyExistException {
        // Parse the authorisation response from the callback URI
        String str = httpServletRequest.getRequestURI() + "?" + httpServletRequest.getQueryString();
        String id = oAuth2Service.getId(type, str);
        //获取到其他平台的用户id之后
        //查找该账户是否已经绑定本地账户,如果没有绑定本地账户,则进行注册新账户并绑定第三方平台id
        AbstractUserRegisterService service = userRegisterFactory.getService(RegisterTypeEnum.OAUTH2);
        boolean userRegistered = service.isUserRegistered(id);
        Long uid;
        if (userRegistered){
            uid = userService.getUserIdByGithubId(id);
        }else{
            uid = service.registerUser(id, null, type);
        }
        StpUtil.login(uid);
        return Results.success();
    }
}