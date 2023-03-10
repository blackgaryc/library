package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookTagsEntity;
import com.blackgaryc.library.service.BookTagsService;
import com.blackgaryc.library.mapper.BookTagsMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【book_tags】的数据库操作Service实现
* @createDate 2023-02-28 16:08:18
*/
@Service
public class BookTagsServiceImpl extends ServiceImpl<BookTagsMapper, BookTagsEntity>
    implements BookTagsService{

}




