package com.blackgaryc.library.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.admin.tag.TagAddDto;
import com.blackgaryc.library.domain.admin.tag.TagSelectVo;
import com.blackgaryc.library.entity.TagEntity;

import java.util.List;

public interface AdminTagService {
    List<TagSelectVo> getSelectData();

    Page<TagSelectVo> getPageData();

    void addTag(TagAddDto dto);

    void updateTag(Integer id, TagAddDto dto);

    TagEntity getInfo(Integer id);

    void deleteTag(Integer id);
}
