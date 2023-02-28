package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.CategoryEntity;
import com.blackgaryc.library.service.CategoryService;
import com.blackgaryc.library.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-02-28 16:08:18
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService{

}




