package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.core.elasticsearch.repository.BookRepository;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.PageableResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.book.UpdateBookRequest;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.tools.ThumbnailTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SaCheckLogin
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Resource
    BookService bookService;
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
    public PageableResult<Book> searchBookByTitleOrMd5(String data,
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
        Assert.notNull(bookEntity,"unable to find book by id="+ request.getId());
        bookEntity.setThumbnail(request.getThumbnail());
        bookService.updateById(bookEntity);
        return Results.successData(bookEntity);
    }

    @Resource
    ThumbnailTools thumbnailTools;

    @GetMapping("test")
    @SaIgnore
    public BaseResult setBookCoverFromBookFile(Long bookId){
        thumbnailTools.generateThumbnailFormFile(bookId);
        return Results.success();
    }

    /***
     * book detail information
     * @param id book id
     * @return
     */
    @GetMapping("{id}")
    public BaseResult queryBookInfo(@PathVariable Long id){

        return Results.success();
    }
}

