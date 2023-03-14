package com.blackgaryc.library.myservice;

import com.blackgaryc.library.domain.category.CategoryNode;

public interface UserCategoryService {
    CategoryNode getCategoryNode(Integer rootId);
}
