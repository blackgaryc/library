package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.myservice.AdminCategoryService;
import com.blackgaryc.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    CategoryService categoryService;
    @Override
    public List<CategorySelectVo> getSelectData() {
        return categoryService.getSelectData();
    }
}
