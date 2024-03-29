package com.blackgaryc.library.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.admin.publisher.PublisherSelectVo;
import com.blackgaryc.library.domain.admin.publisher.PublisherVo;
import com.blackgaryc.library.entity.PublisherEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author alex
* @description 针对表【basics_publisher】的数据库操作Mapper
* @createDate 2023-04-01 14:09:17
* @Entity com.blackgaryc.library.entity.PublisherEntity
*/
public interface PublisherMapper extends BaseMapper<PublisherEntity> {

    List<PublisherSelectVo> getSelectData();

    Page<PublisherVo> getPageData(@Param(value = "page") Page page);
}




