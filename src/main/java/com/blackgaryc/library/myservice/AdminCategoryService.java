package com.blackgaryc.library.myservice;

import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.domain.admin.category.CategoryUpdDto;

import java.util.List;

public interface AdminCategoryService {
    List<CategorySelectVo> getSelectData();

    void addItem(CategoryUpdDto dto);

    void updateItem(Integer id, CategoryUpdDto dto);

    CategoryUpdDto getInfo(Integer id);

    void deleteItem(Integer id);
}
