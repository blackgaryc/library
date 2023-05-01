package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.domain.admin.tag.TagSelectVo;
import com.blackgaryc.library.myservice.AdminCategoryService;
import com.blackgaryc.library.myservice.AdminTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/tag")
public class AdminTagController {
    @Autowired
    AdminTagService adminTagService;
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public BaseResult getSelectData(){
        List<TagSelectVo> list = adminTagService.getSelectData();
        return Results.successData(list);
    }
}
