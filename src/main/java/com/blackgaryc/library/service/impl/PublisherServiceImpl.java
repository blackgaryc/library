package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.domain.admin.publisher.PublisherSelectVo;
import com.blackgaryc.library.domain.admin.publisher.PublisherVo;
import com.blackgaryc.library.entity.PublisherEntity;
import com.blackgaryc.library.service.PublisherService;
import com.blackgaryc.library.mapper.PublisherMapper;
import com.blackgaryc.library.tools.context.HttpContextTool;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author alex
* @description 针对表【basics_publisher】的数据库操作Service实现
* @createDate 2023-04-01 14:09:17
*/
@Service
public class PublisherServiceImpl extends ServiceImpl<PublisherMapper, PublisherEntity>
    implements PublisherService{
    @Override
    public List<PublisherSelectVo> getSelectData() {
        return this.baseMapper.getSelectData();
    }

    @Override
    public Page<PublisherVo> getPageData() {
        return this.baseMapper.getPageData(HttpContextTool.getDefaultPage());
    }
}




