package com.blackgaryc.library.myservice;

import com.blackgaryc.library.domain.admin.category.CategorySelectVo;

import java.util.List;

public interface AdminCategoryService {
    List<CategorySelectVo> getSelectData();
}
