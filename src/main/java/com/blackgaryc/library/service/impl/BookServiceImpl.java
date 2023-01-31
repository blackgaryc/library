package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【book】的数据库操作Service实现
* @createDate 2023-01-27 12:35:59
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity>
    implements BookService{

}




