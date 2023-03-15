package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.publisher.Publisher;
import com.blackgaryc.library.domain.publisher.SimplePublisher;
import com.blackgaryc.library.entity.PublisherEntity;
import com.blackgaryc.library.myservice.UserPublisherService;
import com.blackgaryc.library.service.PublisherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPublisherServiceImpl implements UserPublisherService {
    @Autowired
    PublisherService publisherService;

    @Override
    public List<SimplePublisher> getSimplePublisherList() {
        List<PublisherEntity> list = publisherService.lambdaQuery().list();
        return list.stream().map(SimplePublisher::new).filter(simplePublisher -> simplePublisher.getId() > 0).toList();
    }

    @Override
    public Publisher getPublisherDetail(Integer id) {
        PublisherEntity byId = publisherService.getById(id);
        Publisher target = new Publisher();
        BeanUtils.copyProperties(byId, target);
        return target;
    }
}
