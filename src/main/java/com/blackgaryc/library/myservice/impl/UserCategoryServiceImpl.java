package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.domain.category.Category;
import com.blackgaryc.library.domain.category.CategoryNode;
import com.blackgaryc.library.myservice.UserCategoryService;
import com.blackgaryc.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {
    @Autowired
    CategoryService categoryService;
    @Override
    public CategoryNode getCategoryNode(Integer rootId) {
        List<Category> categories = categoryService.list().stream().map(Category::new).toList();
        return CategoryNode.list2Node(categories,rootId);
    }
}
