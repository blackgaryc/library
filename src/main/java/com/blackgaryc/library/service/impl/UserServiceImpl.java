package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.service.UserService;
import com.blackgaryc.library.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-04-01 14:10:11
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserService{

}




