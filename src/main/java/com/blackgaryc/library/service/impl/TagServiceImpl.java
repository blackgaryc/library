package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.TagEntity;
import com.blackgaryc.library.service.TagService;
import com.blackgaryc.library.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author alex
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2023-02-28 16:08:18
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity>
    implements TagService{

}




