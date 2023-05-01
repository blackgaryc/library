package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.BookTagsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author alex
* @description 针对表【basics_book_tags】的数据库操作Mapper
* @createDate 2023-04-01 14:09:17
* @Entity com.blackgaryc.library.entity.BookTagsEntity
*/
public interface BookTagsMapper extends BaseMapper<BookTagsEntity> {

    void deleteAllByBookId(@Param(value = "id") Long id);
}




