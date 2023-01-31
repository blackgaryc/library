package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.CategoryEntity;
import com.blackgaryc.library.service.CategoryService;
import com.blackgaryc.library.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-01-27 12:35:59
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity>
    implements CategoryService{

}




