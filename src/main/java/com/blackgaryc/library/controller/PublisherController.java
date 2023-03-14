package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.publisher.Publisher;
import com.blackgaryc.library.domain.publisher.SearchPublisher;
import com.blackgaryc.library.domain.publisher.SimplePublisher;
import com.blackgaryc.library.entity.PublisherEntity;
import com.blackgaryc.library.service.PublisherService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("publisher")
public class PublisherController {
    @Resource
    PublisherService publisherService;

    /**
     * 获取所有出版社
     * @return BaseResult
     */
    @GetMapping("list")
    public BaseResult getList(){
        List<PublisherEntity> list = publisherService.lambdaQuery().list();
        List<SimplePublisher> list1 = list.stream().map(SimplePublisher::new).toList();
        return Results.successData(list1);
    }

    /**
     * 获取出版社的详细信息
     */
    @GetMapping("{id}")
    public BaseResult getOne(@PathVariable(value = "id") Integer id){
        PublisherEntity byId = publisherService.getById(id);
        Publisher target = new Publisher();
        BeanUtils.copyProperties(byId, target);
        return Results.successData(target);
    }

}
