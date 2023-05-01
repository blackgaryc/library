package com.blackgaryc.library.mapper;

import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author alex
* @description 针对表【basics_category】的数据库操作Mapper
* @createDate 2023-04-01 14:09:17
* @Entity com.blackgaryc.library.entity.CategoryEntity
*/
public interface CategoryMapper extends BaseMapper<CategoryEntity> {

    List<CategorySelectVo> getSelectData(Integer pid);
}




