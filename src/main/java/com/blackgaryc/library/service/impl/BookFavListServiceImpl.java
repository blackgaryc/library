package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookFavListEntity;
import com.blackgaryc.library.service.BookFavListService;
import com.blackgaryc.library.mapper.BookFavListMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【book_fav_list(图书收藏表)】的数据库操作Service实现
* @createDate 2023-03-19 11:25:27
*/
@Service
public class BookFavListServiceImpl extends ServiceImpl<BookFavListMapper, BookFavListEntity>
    implements BookFavListService{

}




