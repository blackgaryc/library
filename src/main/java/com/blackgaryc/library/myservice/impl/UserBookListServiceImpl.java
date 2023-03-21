package com.blackgaryc.library.myservice.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.domain.book.SimpleBook;
import com.blackgaryc.library.domain.booklist.CreateBookListRequest;
import com.blackgaryc.library.domain.booklist.SimpleBookList;
import com.blackgaryc.library.domain.booklist.UpdateBookListRequest;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.BooklistBooksEntity;
import com.blackgaryc.library.entity.BooklistEntity;
import com.blackgaryc.library.myservice.UserBookListService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.BooklistBooksService;
import com.blackgaryc.library.service.BooklistService;
import com.blackgaryc.library.tools.context.HttpContextTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBookListServiceImpl implements UserBookListService {
    @Autowired
    BooklistService booklistService;
    @Autowired
    BookService bookService;

    @Autowired
    BooklistBooksService booklistBooksService;
    @Override
    public void create(CreateBookListRequest req) {
        BooklistEntity booklistEntity = new BooklistEntity();
        booklistEntity.setName(req.getName());
        booklistEntity.setDescription(req.getDescription());
        booklistEntity.setUid(StpUtil.getLoginIdAsLong());
        booklistEntity.setPublished(req.getPublish() ? 1 : 0);
        booklistService.save(booklistEntity);
    }

    @Override
    public BooklistEntity getById(String id) throws LibraryException {
        BooklistEntity byId = booklistService.getById(id);
        if (null==byId || !byId.getUid().equals(StpUtil.getLoginIdAsLong())){
            throw new LibraryException("无法找到该书单");
        }
        return byId;
    }

    @Override
    public void update(UpdateBookListRequest req) throws LibraryException {
        BooklistEntity byId = this.getById(req.getId());
        byId.setPublished(req.getPublish() ? 1 : 0);
        byId.setDescription(req.getDescription());
        byId.setName(req.getName());
        booklistService.updateById(byId);
    }

    @Override
    public Page<BooklistEntity> listByPage() {
        return booklistService.lambdaQuery()
                .eq(BooklistEntity::getUid, StpUtil.getLoginIdAsLong())
                .page(HttpContextTool.getDefaultPage());
    }

    @Override
    @Transactional
    public void addBook2BookList(String id, Long bookId) throws LibraryException {
        BooklistEntity byId = this.getById(id);
        BookEntity book = this.getBookEntityById(bookId);
        //创建关系并保存
        BooklistBooksEntity booklistBooksEntity = new BooklistBooksEntity();
        booklistBooksEntity.setBookId(bookId);
        booklistBooksEntity.setBooklistId(id);
        try {
            booklistBooksService.save(booklistBooksEntity);
        }catch (DuplicateKeyException exception){
            throw new LibraryException("已保存");
        }
    }

    @Override
    @Transactional
    public void deleteBookInBookList(String id, Long bookId) throws LibraryException {
        BooklistEntity byId = this.getById(id);
        BooklistBooksEntity one = booklistBooksService.lambdaQuery()
                .eq(BooklistBooksEntity::getBooklistId, byId.getId())
                .eq(BooklistBooksEntity::getBookId, bookId).one();
        if (null == one){
            throw new LibraryException("该书单不存在该书籍。");
        }
        booklistBooksService.remove(Wrappers.lambdaQuery(one));
    }

    @Override
    public Page<SimpleBook> listBookInBookList(String id) throws LibraryException {
        BooklistEntity byId = this.getById(id);
        if (byId.getPublished()==0 && StpUtil.isLogin() && byId.getUid()!=StpUtil.getLoginIdAsLong()){
            throw  new LibraryException("无权限查看该书单");
        }
        Page<BooklistBooksEntity> booksEntityPage = booklistBooksService.lambdaQuery()
                .eq(BooklistBooksEntity::getBooklistId, id)
                .page(HttpContextTool.getDefaultPage());
        List<Long> collect = booksEntityPage.getRecords().stream()
                .map(BooklistBooksEntity::getBookId)
                .collect(Collectors.toList());
        List<SimpleBook> bookEntities= Collections.emptyList();
        if (collect.size()>0){
            bookEntities = bookService.listByIds(collect).stream().map(SimpleBook::new).collect(Collectors.toList());
        }
        Page<SimpleBook> res = Page.of(booksEntityPage.getCurrent(), booksEntityPage.getSize());
        res.setPages(booksEntityPage.getPages());
        res.setTotal(booksEntityPage.getTotal());
        res.setRecords(bookEntities);
        return res;
    }

    @Override
    public void deleteBookList(String id) throws LibraryException {
        BooklistEntity byId = this.getById(id);
        booklistService.removeById(byId.getId());
    }

    @Override
    public Page<BooklistEntity> getAllPublishedBookList() {
        Page<BooklistEntity> page = booklistService.lambdaQuery()
                .eq(BooklistEntity::getPublished,1)
                .page(HttpContextTool.getDefaultPage());
        return page;
    }

    private BookEntity getBookEntityById(Long bookId) throws LibraryException {
        BookEntity book = bookService.getById(bookId);
        if (null==book){
            throw new LibraryException("书籍不存在");
        }
        return book;
    }
}
