package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.AuthorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【author】的数据库操作Mapper
* @createDate 2023-01-27 12:35:59
* @Entity generator.entity.AuthorEntity
*/
@Mapper
public interface AuthorMapper extends BaseMapper<AuthorEntity> {

}




