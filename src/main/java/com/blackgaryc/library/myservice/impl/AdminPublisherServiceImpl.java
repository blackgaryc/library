package com.blackgaryc.library.myservice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.domain.admin.publisher.PublisherDto;
import com.blackgaryc.library.domain.admin.publisher.PublisherSelectVo;
import com.blackgaryc.library.domain.admin.publisher.PublisherVo;
import com.blackgaryc.library.entity.PublisherEntity;
import com.blackgaryc.library.myservice.AdminPublisherService;
import com.blackgaryc.library.service.PublisherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPublisherServiceImpl implements AdminPublisherService {
    @Autowired
    PublisherService publisherService;
    @Override
    public List<PublisherSelectVo> getSelectData() {
        return publisherService.getSelectData();
    }

    @Override
    public Page<PublisherVo> getPageData() {
        return publisherService.getPageData();
    }

    @Override
    public void addPublisher(PublisherDto dto) {
        PublisherEntity publisherEntity = new PublisherEntity();
        BeanUtils.copyProperties(dto,publisherEntity);
        publisherService.save(publisherEntity);
    }

    @Override
    public PublisherDto getInfo(Integer id) {
        PublisherEntity byId = publisherService.getById(id);
        PublisherDto publisherDto = new PublisherDto();
        BeanUtils.copyProperties(byId,publisherDto);
        return publisherDto;
    }

    @Override
    public void updatePublisher(Integer id, PublisherDto dto) {
        PublisherEntity byId = publisherService.getById(id);
        BeanUtils.copyProperties(dto,byId);
        publisherService.updateById(byId);
    }

    @Override
    public void deletePublisher(Integer id) {
        publisherService.removeById(id);
    }
}
