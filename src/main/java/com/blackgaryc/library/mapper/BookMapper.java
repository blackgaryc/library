package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.BookEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author alex
* @description 针对表【book】的数据库操作Mapper
* @createDate 2023-02-23 15:34:04
* @Entity com.blackgaryc.library.entity.BookEntity
*/
@Mapper
public interface BookMapper extends BaseMapper<BookEntity> {

}




