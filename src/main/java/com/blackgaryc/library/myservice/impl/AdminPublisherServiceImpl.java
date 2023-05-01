package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.domain.admin.publisher.PublisherSelectVo;
import com.blackgaryc.library.myservice.AdminPublisherService;
import com.blackgaryc.library.service.PublisherService;
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
}
