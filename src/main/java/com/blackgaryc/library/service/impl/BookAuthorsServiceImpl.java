package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookAuthorsEntity;
import com.blackgaryc.library.service.BookAuthorsService;
import com.blackgaryc.library.mapper.BookAuthorsMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【book_authors】的数据库操作Service实现
* @createDate 2023-02-25 11:20:50
*/
@Service
public class BookAuthorsServiceImpl extends ServiceImpl<BookAuthorsMapper, BookAuthorsEntity>
    implements BookAuthorsService{

}




