package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.myservice.AdminBookSearchService;
import com.blackgaryc.library.myservice.UserBookService;
import com.blackgaryc.library.myservice.UserFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class AdminBookSearchServiceImpl implements AdminBookSearchService {
    @Resource
    UserBookService userBookService;

    @Resource
    UserFileService adminFileService;

    @Override
    public Book getBook4ElasticSearchByBookEntity(BookEntity bookEntity) {
        List<BookDetailEntity> entities = userBookService.getBookDetailEntitiesByBookEntity(bookEntity);
        List<FileEntity> fileEntitiesByIds = adminFileService.getFileEntitiesByIds(
                entities.stream().map(BookDetailEntity::getFileId).toList());
        return new Book(bookEntity,entities,fileEntitiesByIds);
    }

    @Override
    public List<Book> getBook4ElasticSearchByBookEntities(Collection<BookEntity> bookEntities) {
        List<Long> bookIds = bookEntities.stream().map(BookEntity::getId).toList();
        List<BookDetailEntity> bookDetailEntities = userBookService.getBookDetailEntitiesByBookIds(bookIds);
        List<String> fileIds = bookDetailEntities.stream().map(BookDetailEntity::getFileId).toList();
        List<FileEntity> fileEntitiesByIds = adminFileService.getFileEntitiesByIds(fileIds);
        //数据转换
        return bookEntities.stream().map(bookEntity -> new Book(bookEntity, bookDetailEntities, fileEntitiesByIds)).toList();
    }
}
