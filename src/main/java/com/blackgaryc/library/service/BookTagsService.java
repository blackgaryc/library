package com.blackgaryc.library.service;

import com.blackgaryc.library.entity.BookTagsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author alex
* @description 针对表【basics_book_tags】的数据库操作Service
* @createDate 2023-04-01 14:09:17
*/
public interface BookTagsService extends IService<BookTagsEntity> {

    void deleteAllByBookId(Long id);
}
