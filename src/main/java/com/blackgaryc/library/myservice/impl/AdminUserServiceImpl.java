package com.blackgaryc.library.myservice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.domain.user.admin.SimpleUserVO;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.myservice.AdminUserService;
import com.blackgaryc.library.service.UserService;
import com.blackgaryc.library.tools.context.HttpContextTool;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    UserService userService;
    @Override
    public Page<UserEntity> getPageList(String name) {
        return userService.lambdaQuery()
                .like(Strings.isNotBlank(name),UserEntity::getAccount,name)
                .page(HttpContextTool.getDefaultPage());
    }

    @Override
    public void disableUser(Long id) throws LibraryException {
        UserEntity byId = this.userService.getById(id);
        if (null == byId){
            throw new LibraryException("用户不存在");
        }
        byId.setDisabled(true);
        userService.updateById(byId);
    }

    @Override
    public void enableUser(Long id) {
        UserEntity byId = this.userService.getById(id);
        byId.setDisabled(false);
        userService.updateById(byId);
    }

    @Override
    public SimpleUserVO getUserInfo(Long id) {
        UserEntity byId = this.userService.getById(id);
        return new SimpleUserVO(byId);
    }

    @Override
    public void updateUserInfo(SimpleUserVO dto) {
        UserEntity byId = userService.getById(dto.getId());
        BeanUtils.copyProperties(dto,byId);
        userService.updateById(byId);
    }
}
