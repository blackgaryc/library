package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BooklistEntity;
import com.blackgaryc.library.service.BooklistService;
import com.blackgaryc.library.mapper.BooklistMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【booklist】的数据库操作Service实现
* @createDate 2023-02-23 15:34:04
*/
@Service
public class BooklistServiceImpl extends ServiceImpl<BooklistMapper, BooklistEntity>
    implements BooklistService{

}




