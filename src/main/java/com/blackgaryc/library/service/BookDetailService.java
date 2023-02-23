package com.blackgaryc.library.service;

import com.blackgaryc.library.entity.BookDetailEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author alex
* @description 针对表【book_detail】的数据库操作Service
* @createDate 2023-02-23 15:34:04
*/
public interface BookDetailService extends IService<BookDetailEntity> {
    BookDetailEntity findByFileId(Long id);
    List<BookDetailEntity> findAllByBookId(Long bookId);

}
