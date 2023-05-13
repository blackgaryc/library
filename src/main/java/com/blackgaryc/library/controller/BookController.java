package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.core.elasticsearch.repository.BookRepository;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.PageableResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.book.UpdateBookRequest;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.myservice.UserBookService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.tools.context.HttpContextTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SaCheckLogin
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;//搜索
    @Resource
    BookService bookService;
    @Resource
    UserBookService userBookService;

    /**
     * main search entry
     *
     * @param data 搜索的内容
     * @return 搜索的分页数据
     */
    @GetMapping("search")
    @SaIgnore
    public PageableResult<Book> searchBookByTitleOrMd5(String data) {
        return Results.successSpringbootPageData(bookRepository.findByTitleOrMd5(data, HttpContextTool.getDefaultPageable()));
    }

    /**
     * update book describe or book detail information,
     * post data that which need to be updated.
     *
     * @return 更新书籍的信息
     */
    @PostMapping("update")
    public BaseResult updateBook(@RequestBody UpdateBookRequest request) {
        //TODO 提交到mq，由管理手动审核完成后，修改生效
        BookEntity bookEntity = bookService.getBaseMapper().selectById(request.getId());
        Assert.notNull(bookEntity, "unable to find book by id=" + request.getId());
        bookEntity.setThumbnail(request.getThumbnail());
        bookService.updateById(bookEntity);
        return Results.success();
    }

    /***
     * book detail information
     * @param id book id
     * @return 返回一本书的详情
     */
    @GetMapping("{id}")
    @SaIgnore
    public BaseResult queryBookInfo(@PathVariable Long id) throws LibraryException {
        return Results.successData(userBookService.getBookDetail(id));
    }

    /**
     *
     * @return 返回最新审核完成的书籍
     */
    @GetMapping("latest")
    @SaIgnore
    public BaseResult latestBooks() {
        return Results.successData(userBookService.getLatestBooks());
    }

    @GetMapping("download/history")
    @SaIgnore
    public BaseResult getBookDownloadHistory() {
        return Results.successData(userBookService.getBookDownloadHistory());
    }
}

