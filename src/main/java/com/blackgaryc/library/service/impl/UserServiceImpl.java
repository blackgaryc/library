package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.UserEntity;
import com.blackgaryc.library.service.UserService;
import com.blackgaryc.library.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-01-27 12:35:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserService{

}




