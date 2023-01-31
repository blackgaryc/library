package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.TagEntity;
import com.blackgaryc.library.service.TagService;
import com.blackgaryc.library.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2023-01-27 12:35:59
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity>
    implements TagService{

}




