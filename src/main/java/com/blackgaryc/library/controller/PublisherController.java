package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.myservice.UserPublisherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("publisher")
public class PublisherController {
    @Resource
    UserPublisherService userPublisherService;

    /**
     * 获取所有出版社
     * @return BaseResult
     */
    @GetMapping("list")
    public BaseResult getList(){
        return Results.successData(userPublisherService.getSimplePublisherList());
    }

    /**
     * 获取出版社的详细信息
     * @param id 出版社id
     * @return 返回出版社的具体信息
     */
    @GetMapping("{id}")
    public BaseResult getOne(@PathVariable(value = "id") Integer id){
        return Results.successData(userPublisherService.getPublisherDetail(id));
    }

}
