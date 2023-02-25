package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.core.elasticsearch.repository.BookRepository;
import com.blackgaryc.library.core.error.BookNotExistException;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.PageableResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.book.UpdateBookRequest;
import com.blackgaryc.library.entity.BookDetailEntity;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.BookStatusEnum;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.service.BookDetailService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.tools.ThumbnailTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@SaCheckLogin
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Resource
    BookService bookService;
    @Resource
    BookDetailService bookDetailService;
    @Resource
    FileService fileService;

    /**
     * main search entry
     *
     * @param data
     * @param page
     * @param size
     * @return
     */
    @GetMapping("search")
    @SaIgnore
    public PageableResult searchBookByTitleOrMd5(String data,
                                                       @RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "50") Integer size) {
        page = page < 0 ? 0 : page;
        size = size > 100 ? 100 : size;
        Page<Book> pageResult = bookRepository.findByTitleOrMd5(data, PageRequest.of(page, size));
        return new PageableResult<>(pageResult);
    }

    /**
     * update book describe or book detail information,
     * post data that which need to be updated.
     *
     * @return
     */
    @PostMapping("update")
    public BaseResult updateBook(@RequestBody UpdateBookRequest request) {
        BookEntity bookEntity = bookService.getBaseMapper().selectById(request.getId());
        Assert.notNull(bookEntity, "unable to find book by id=" + request.getId());
        bookEntity.setThumbnail(request.getThumbnail());
        bookService.updateById(bookEntity);
        return Results.successData(bookEntity);
    }

    @Resource
    ThumbnailTools thumbnailTools;

    @GetMapping("test")
    @SaIgnore
    public BaseResult setBookCoverFromBookFile(Long bookId) {
        thumbnailTools.generateThumbnailFormFile(bookId);
        return Results.success();
    }

    /***
     * book detail information
     * @param id book id
     * @return
     */
    @GetMapping("{id}")
    @SaIgnore
    public BaseResult queryBookInfo(@PathVariable Long id) throws LibraryException {
        BookEntity bookEntity = bookService.getById(id);
        if (null == bookEntity || bookEntity.getStatus() == -1) {
            //被下架，抛出文件不存在的异常
            throw new BookNotExistException(id);
        }
        if (bookEntity.getStatus() == 0) {
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
            return Results.successData(
                    com.blackgaryc.library.domain.book.Book.NoFiles(bookEntity)
            );
        }
        List<FileEntity> fileEntities = fileService.listByIds(bookDetails.stream().map(BookDetailEntity::getFileId).toList());
        return Results.successData(
                com.blackgaryc.library.domain.book.Book.HasFiles(
                        bookEntity,
                        fileEntities
                ));
    }

    @GetMapping("latest")
    @SaIgnore
    public BaseResult latestBooks(){
        List<BookEntity> list = bookService.lambdaQuery()
                .orderByDesc(true,BookEntity::getCreateTime)
                .eq(BookEntity::getStatus, BookStatusEnum.ENABLE.getCode())
                .last("limit 25").list();
        return Results.successData(list);
    }
}

