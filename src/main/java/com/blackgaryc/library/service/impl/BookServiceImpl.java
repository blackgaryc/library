package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【book】的数据库操作Service实现
* @createDate 2023-02-25 11:20:50
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity>
    implements BookService{

}




