package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author alex
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-02-23 15:34:04
* @Entity com.blackgaryc.library.entity.UserEntity
*/
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    boolean existUserByAccount(String account);
    UserEntity selectByAccount(String account);
}




