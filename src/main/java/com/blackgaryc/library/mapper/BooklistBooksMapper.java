package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.BooklistBooksEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author alex
* @description 针对表【booklist_books】的数据库操作Mapper
* @createDate 2023-02-23 15:34:04
* @Entity com.blackgaryc.library.entity.BooklistBooksEntity
*/
@Mapper
public interface BooklistBooksMapper extends BaseMapper<BooklistBooksEntity> {

}




