package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.category.Category;
import com.blackgaryc.library.domain.category.CategoryNode;
import com.blackgaryc.library.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    CategoryService categoryService;
    @GetMapping("tree")
    public BaseResult getTree(){
        List<Category> categories = categoryService.list().stream().map(Category::new).toList();
        return Results.successData(CategoryNode.list2Node(categories));
    }
}
