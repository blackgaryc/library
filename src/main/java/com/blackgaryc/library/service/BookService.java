package com.blackgaryc.library.service;

import com.blackgaryc.library.domain.admin.book.BookDto;
import com.blackgaryc.library.entity.BookEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author alex
* @description 针对表【basics_book】的数据库操作Service
* @createDate 2023-04-01 14:09:17
*/
public interface BookService extends IService<BookEntity> {

    BookDto getBookInfo(Long id);
}
