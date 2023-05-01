package com.blackgaryc.library.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.book.BookDto;
import com.blackgaryc.library.domain.user.admin.BookUpdateDto;
import com.blackgaryc.library.domain.user.admin.SimpleBookVO;
import com.blackgaryc.library.myservice.AdminBookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("admin/book")
public class AdminBookController {
    @Resource
    AdminBookService adminBookService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResult getList(String name){
        Page<SimpleBookVO> pageList=this.adminBookService.getPageList(name);
        return Results.successMybatisPageData(pageList);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public BaseResult bookDelete(@PathVariable Long id) throws LibraryException {
        this.adminBookService.deleteBook(id);
        return Results.success();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public BaseResult bookIndexGet(@PathVariable Long id) throws LibraryException {
        BookDto bookInfo =  this.adminBookService.getBookInfo(id);
        return Results.successData(bookInfo);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public BaseResult book(@PathVariable Long id) throws LibraryException {
        adminBookService.changeStatus(id);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public BaseResult indexDelete(@PathVariable Long id,
                                  @RequestBody BookUpdateDto bookUpdateDto) throws LibraryException {
        adminBookService.updateBookInfo(id,bookUpdateDto);
        return Results.success();
    }

}
