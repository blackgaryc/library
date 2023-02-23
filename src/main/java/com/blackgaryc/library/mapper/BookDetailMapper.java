package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.BookDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
* @author alex
* @description 针对表【book_detail】的数据库操作Mapper
* @createDate 2023-02-23 15:34:04
* @Entity com.blackgaryc.library.entity.BookDetailEntity
*/
@Mapper
public interface BookDetailMapper extends BaseMapper<BookDetailEntity> {

    BookDetailEntity findByFileId(Long id);
    List<BookDetailEntity> findAllByBookId(Long bookId);
}




