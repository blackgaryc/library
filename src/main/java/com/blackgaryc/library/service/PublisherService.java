package com.blackgaryc.library.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.admin.publisher.PublisherSelectVo;
import com.blackgaryc.library.domain.admin.publisher.PublisherVo;
import com.blackgaryc.library.entity.PublisherEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author alex
* @description 针对表【basics_publisher】的数据库操作Service
* @createDate 2023-04-01 14:09:17
*/
public interface PublisherService extends IService<PublisherEntity> {

    List<PublisherSelectVo> getSelectData();

    Page<PublisherVo> getPageData();
}
