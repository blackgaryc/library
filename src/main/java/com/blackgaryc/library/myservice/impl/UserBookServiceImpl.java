package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.myservice.UserBookService;
import com.blackgaryc.library.service.BookDetailService;
import com.blackgaryc.library.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserBookServiceImpl implements UserBookService {

    @Resource
    BookDetailService bookDetailService;
    @Resource
    BookService bookService;

    @Override
    public Optional<BookEntity> getBookEntityByFileId(String fileId) {
        BookDetailEntity entity = bookDetailService.lambdaQuery()
                .select(BookDetailEntity::getBookId)
                .eq(BookDetailEntity::getFileId, fileId)
                .getEntity();
        if (Objects.isNull(entity)) {
            return Optional.empty();
        }
        BookEntity byId = bookService.getById(entity.getBookId());
        return Optional.of(byId);
    }

    @Override
    public List<FileEntity> getFileEntityByBookId(String bookId) {

        return Collections.emptyList();
    }

    @Override
    public List<BookDetailEntity> getBookDetailEntitiesByBookIds(Collection<Long> bookIds) {
        return bookDetailService.lambdaQuery().in(BookDetailEntity::getBookId, bookIds).list();
    }

    @Override
    public List<BookDetailEntity> getBookDetailEntitiesByBookEntity(BookEntity bookEntity) {
        return bookDetailService.lambdaQuery().eq(BookDetailEntity::getBookId, bookEntity.getId()).list();
    }
}
