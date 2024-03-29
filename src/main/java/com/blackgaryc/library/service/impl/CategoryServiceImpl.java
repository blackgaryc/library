package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.domain.admin.category.CategorySelectVo;
import com.blackgaryc.library.entity.CategoryEntity;
import com.blackgaryc.library.service.CategoryService;
import com.blackgaryc.library.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author alex
* @description 针对表【basics_category】的数据库操作Service实现
* @createDate 2023-04-01 14:09:17
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService{
    @Override
    public List<CategorySelectVo> getSelectData() {
        return this.baseMapper.getSelectData(0);
    }
}




