package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.myservice.UserCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    UserCategoryService userCategoryService;

    /**
     * 查询分类
     * @param id 分类的父级id，id不存在或者非法时，id为0
     * @return 返回树状分类结果
     */
    @GetMapping("tree")
    public BaseResult getTree(@RequestParam(defaultValue = "0") Integer id) {
        return Results.successData(userCategoryService.getCategoryNode(id));
    }
}
