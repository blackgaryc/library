package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.service.BookDetailService;
import com.blackgaryc.library.mapper.BookDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【book_detail】的数据库操作Service实现
* @createDate 2023-02-28 16:08:18
*/
@Service
public class BookDetailServiceImpl extends ServiceImpl<BookDetailMapper, BookDetailEntity>
    implements BookDetailService{

}




