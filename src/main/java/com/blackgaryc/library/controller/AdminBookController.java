package com.blackgaryc.library.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.user.admin.BookUpdateDto;
import com.blackgaryc.library.domain.user.admin.SimpleBookVO;
import com.blackgaryc.library.myservice.AdminBookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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
    public BaseResult indexDelete(@PathVariable Long id) throws LibraryException {
        this.adminBookService.deleteBook(id);
        return Results.success();
    }
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public BaseResult indexDelete(@PathVariable Long id,
                                  @RequestBody BookUpdateDto bookUpdateDto) throws LibraryException {
        return Results.success();
    }

}
