package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.domain.admin.tag.TagSelectVo;
import com.blackgaryc.library.myservice.AdminTagService;
import com.blackgaryc.library.service.TagService;
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
}
