package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.domain.admin.tag.TagSelectVo;
import com.blackgaryc.library.entity.TagEntity;
import com.blackgaryc.library.service.TagService;
import com.blackgaryc.library.mapper.TagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author alex
* @description 针对表【basics_tag】的数据库操作Service实现
* @createDate 2023-04-01 14:09:17
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity>
    implements TagService{
    @Override
    public List<TagSelectVo> getSelectData() {
        return this.baseMapper.getSelectData();
    }
}




