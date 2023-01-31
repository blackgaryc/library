package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author blackgaryc
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-01-27 12:35:59
* @Entity generator.entity.UserEntity
*/
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}




