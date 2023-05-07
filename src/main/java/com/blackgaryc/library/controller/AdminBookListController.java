package com.blackgaryc.library.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.user.admin.BookUpdateDto;
import com.blackgaryc.library.domain.user.admin.SimpleBookVO;
import com.blackgaryc.library.entity.BooklistEntity;
import com.blackgaryc.library.myservice.AdminBookListService;
import com.blackgaryc.library.myservice.AdminBookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("admin/bookList")
public class AdminBookListController {
    @Resource
    AdminBookListService adminBookListService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResult getList(String name){
        Page<BooklistEntity> pageList=this.adminBookListService.getPageList(name);
        return Results.successMybatisPageData(pageList);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public BaseResult indexDelete(@PathVariable Long id) throws LibraryException {
        this.adminBookListService.deleteBookList(id);
        return Results.success();
    }
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public BaseResult indexPut(@PathVariable Long id) throws LibraryException {
        this.adminBookListService.enableBookList(id);
        return Results.success();
    }

}
