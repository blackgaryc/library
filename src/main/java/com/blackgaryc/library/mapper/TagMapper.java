package com.blackgaryc.library.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.admin.tag.TagSelectVo;
import com.blackgaryc.library.entity.TagEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author alex
* @description 针对表【basics_tag】的数据库操作Mapper
* @createDate 2023-04-01 14:09:17
* @Entity com.blackgaryc.library.entity.TagEntity
*/
public interface TagMapper extends BaseMapper<TagEntity> {

    List<TagSelectVo> getSelectData();

    Page<TagSelectVo> getPageData(@Param(value = "page") Page page);
}




