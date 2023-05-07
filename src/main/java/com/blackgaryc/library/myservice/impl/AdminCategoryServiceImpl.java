package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.domain.admin.category.CategoryUpdDto;
import com.blackgaryc.library.entity.CategoryEntity;
import com.blackgaryc.library.myservice.AdminCategoryService;
import com.blackgaryc.library.service.CategoryService;
import org.springframework.beans.BeanUtils;
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

    @Override
    public void addItem(CategoryUpdDto dto) {
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(dto,entity);
        categoryService.save(entity);
    }

    @Override
    public void updateItem(Integer id, CategoryUpdDto dto) {
        CategoryEntity entity = categoryService.getById(id);
        BeanUtils.copyProperties(dto,entity);
        categoryService.updateById(entity);
    }

    @Override
    public CategoryUpdDto getInfo(Integer id) {
        CategoryEntity entity = categoryService.getById(id);
        CategoryUpdDto categoryUpdDto = new CategoryUpdDto();
        BeanUtils.copyProperties(entity,categoryUpdDto);
        return categoryUpdDto;
    }

    @Override
    public void deleteItem(Integer id) {
        categoryService.removeById(id);
    }
}
