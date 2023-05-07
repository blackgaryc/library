package com.blackgaryc.library.myservice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.admin.tag.TagAddDto;
import com.blackgaryc.library.domain.admin.tag.TagSelectVo;
import com.blackgaryc.library.entity.TagEntity;
import com.blackgaryc.library.myservice.AdminTagService;
import com.blackgaryc.library.service.TagService;
import com.blackgaryc.library.tools.context.HttpContextTool;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminTagServiceImpl implements AdminTagService {
    @Autowired
    TagService tagService;
    @Override
    public List<TagSelectVo> getSelectData() {
        return tagService.getSelectData();
    }

    @Override
    public Page<TagSelectVo> getPageData() {
        return tagService.getPageData(HttpContextTool.getDefaultPage());
    }

    @Override
    public void addTag(TagAddDto dto) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(dto.getName());
        tagService.save(tagEntity);
    }

    @Override
    public void updateTag(Integer id, TagAddDto dto) {
        TagEntity byId = tagService.getById(id);
        TagEntity tagEntity = byId;
        tagEntity.setName(dto.getName());
        tagService.updateById(tagEntity);
    }

    @Override
    public TagEntity getInfo(Integer id) {
        return tagService.getById(id);
    }

    @Override
    public void deleteTag(Integer id) {
        tagService.removeById(id);
    }
}
