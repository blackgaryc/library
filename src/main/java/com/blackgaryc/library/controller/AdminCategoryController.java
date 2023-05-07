package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.domain.admin.category.CategoryUpdDto;
import com.blackgaryc.library.myservice.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResult indexPost(@RequestBody CategoryUpdDto dto){
        adminCategoryService.addItem(dto);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public BaseResult indexPost(@PathVariable Integer id, @RequestBody CategoryUpdDto dto){
        adminCategoryService.updateItem(id,dto);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public BaseResult itemGet(@PathVariable Integer id){
        CategoryUpdDto dto = adminCategoryService.getInfo(id);
        return Results.successData(dto);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public BaseResult itemDelete(@PathVariable Integer id){
        adminCategoryService.deleteItem(id);
        return Results.success();
    }
}
