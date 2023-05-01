package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.domain.admin.publisher.PublisherSelectVo;
import com.blackgaryc.library.domain.category.Category;
import com.blackgaryc.library.myservice.AdminCategoryService;
import com.blackgaryc.library.myservice.AdminPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/category")
public class AdminCategoryController {
    @Autowired
    AdminCategoryService adminCategoryService;
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public BaseResult getSelectData(){
        List<CategorySelectVo> list = adminCategoryService.getSelectData();
        return Results.successData(list);
    }
}
