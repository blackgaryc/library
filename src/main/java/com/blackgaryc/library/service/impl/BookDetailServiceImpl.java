package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.service.BookDetailService;
import com.blackgaryc.library.mapper.BookDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【book_detail】的数据库操作Service实现
* @createDate 2023-01-27 12:35:59
*/
@Service
public class BookDetailServiceImpl extends ServiceImpl<BookDetailMapper, BookDetailEntity>
    implements BookDetailService{

}




