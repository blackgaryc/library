package com.blackgaryc.library.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.user.admin.SimpleUserVO;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.myservice.AdminUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("admin/user")
public class AdminUserController {
    @Resource
    AdminUserService adminUserService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResult getList(String name){
        Page<UserEntity> pageList=this.adminUserService.getPageList(name);
        return Results.successMybatisPageData(pageList, SimpleUserVO::new);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public BaseResult indexDelete(@PathVariable Long id) throws LibraryException {
        this.adminUserService.disableUser(id);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public BaseResult userPut(@PathVariable Long id) throws LibraryException {
        this.adminUserService.enableUser(id);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public BaseResult userGet(@PathVariable Long id) throws LibraryException {
        return Results.successData(adminUserService.getUserInfo(id));
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public BaseResult indexPut(@RequestBody SimpleUserVO dto){
        adminUserService.updateUserInfo(dto);
        return Results.success();
    }

}
