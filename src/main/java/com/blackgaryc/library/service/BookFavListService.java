package com.blackgaryc.library.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.entity.BookFavListEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author alex
* @description 针对表【basics_book_fav_list(图书收藏表)】的数据库操作Service
* @createDate 2023-04-01 14:09:17
*/
public interface BookFavListService extends IService<BookFavListEntity> {

    Page<Map<String,Object>> getFavBooks(Page<Object> defaultPage);
}
