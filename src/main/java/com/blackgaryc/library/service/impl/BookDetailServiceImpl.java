package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.service.BookDetailService;
import com.blackgaryc.library.mapper.BookDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author alex
* @description 针对表【book_detail】的数据库操作Service实现
* @createDate 2023-02-23 15:34:04
*/
@Service
public class BookDetailServiceImpl extends ServiceImpl<BookDetailMapper, BookDetailEntity>
    implements BookDetailService{
    @Override
    public List<BookDetailEntity> findAllByBookId(Long bookId) {
        return baseMapper.findAllByBookId(bookId);
    }

    @Override
    public BookDetailEntity findByFileId(Long id) {
        return baseMapper.findByFileId(id);
    }
}




