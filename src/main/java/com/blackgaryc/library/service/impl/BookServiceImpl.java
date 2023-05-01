package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.domain.admin.book.BookDto;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【basics_book】的数据库操作Service实现
* @createDate 2023-04-01 14:09:17
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity>
    implements BookService{
    @Override
    public BookDto getBookInfo(Long id) {
        return this.baseMapper.getBookInfo(id);
    }
}




