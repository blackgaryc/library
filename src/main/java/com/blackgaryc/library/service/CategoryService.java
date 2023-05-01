package com.blackgaryc.library.service;

import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author alex
* @description 针对表【basics_category】的数据库操作Service
* @createDate 2023-04-01 14:09:17
*/
public interface CategoryService extends IService<CategoryEntity> {

    List<CategorySelectVo> getSelectData();
}
