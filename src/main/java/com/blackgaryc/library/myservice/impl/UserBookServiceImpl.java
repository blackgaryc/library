package com.blackgaryc.library.myservice.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.error.BookNotExistException;
import com.blackgaryc.library.domain.book.Book;
import com.blackgaryc.library.domain.book.SimpleBook;
import com.blackgaryc.library.entity.*;
import com.blackgaryc.library.mapper.LogFileDownloadMapper;
import com.blackgaryc.library.myservice.UserBookService;
import com.blackgaryc.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserBookServiceImpl implements UserBookService {

    @Resource
    BookDetailService bookDetailService;
    @Resource
    BookService bookService;
    @Resource
    FileService fileService;

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

    @Cacheable(cacheNames = "book:query:latest")
    @Override
    public List<SimpleBook> getLatestBooks() {
        return bookService.lambdaQuery()
                .orderByDesc(true, BookEntity::getCreateTime)
                .eq(BookEntity::getStatus, StatusEnum.ENABLE.getCode())
                .last("limit 50")
                //转换
                .list().stream().map(SimpleBook::new).toList();
    }

    @Override
    public Book getBookDetail(Long id) throws BookNotExistException {
        BookEntity bookEntity = bookService.getById(id);
        if (null == bookEntity ||
                bookEntity.getStatus() == StatusEnum.DISABLE.getCode() ||
                bookEntity.getStatus() == StatusEnum.DELETED.getCode() ) {
            //被下架，抛出文件不存在的异常
            throw new BookNotExistException(id);
        }
        if (bookEntity.getStatus() == StatusEnum.DEFAULT.getCode()) {
            //待审核，检查是否当前用户为文件上传用户，如果是则允许访问
            if (!StpUtil.isLogin()){
                //没有登陆返回书籍不存在
                throw new BookNotExistException(id);
            }
            long uid = StpUtil.getLoginIdAsLong();
            if (!Long.valueOf(uid).equals(bookEntity.getCreatedUid())){
                //非上传用户也返回书籍不存在
                throw new BookNotExistException(id);
            }
        }
        //正常访问
        //数据库读取书籍
        List<BookDetailEntity> bookDetails = bookDetailService.lambdaQuery()
                .eq(BookDetailEntity::getBookId,bookEntity.getId())
                .list();
        if (bookDetails.isEmpty()){
            return Book.NoFiles(bookEntity);
        }
        List<FileEntity> fileEntities = fileService.listByIds(bookDetails.stream().map(BookDetailEntity::getFileId).toList());
        Book book = Book.HasFiles(bookEntity, fileEntities);
        if(Objects.nonNull(book.getPublisherId())){
            PublisherEntity byId = publisherService.getById(book.getPublisherId());
            if (Objects.nonNull(byId)){
                book.setPublisher(byId.getName());
            }
        }
        if(Objects.nonNull(book.getCategoryId())){
            CategoryEntity byId = categoryService.getById(book.getCategoryId());
            if (Objects.nonNull(byId)){
                book.setCategory(byId.getName());
            }
        }
        return book;
    }

    @Autowired
    PublisherService publisherService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    LogFileDownloadMapper logFileDownloadMapper;
    @Override
    public List<Map<String, Object>> getBookDownloadHistory() {
        return logFileDownloadMapper.getBookDownloadHistory(StpUtil.getLoginIdAsLong());
    }
}
