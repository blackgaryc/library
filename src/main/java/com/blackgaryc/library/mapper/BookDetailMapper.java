package com.blackgaryc.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blackgaryc.library.core.elasticsearch.domain.BookDetail;
import com.blackgaryc.library.entity.BookDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
* @author alex
* @description 针对表【book_detail】的数据库操作Mapper
* @createDate 2023-02-09 21:15:19
* @Entity generator.entity.BookDetailEntity
*/
@Mapper
public interface BookDetailMapper extends BaseMapper<BookDetailEntity> {

    BookDetailEntity findByFileId(Long id);
    List<BookDetailEntity> findAllByBookId(Long bookId);
}




