package com.blackgaryc.library.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.publisher.PublisherDto;
import com.blackgaryc.library.domain.admin.publisher.PublisherSelectVo;
import com.blackgaryc.library.domain.admin.publisher.PublisherVo;
import com.blackgaryc.library.myservice.AdminPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/publisher")
@RestController
public class AdminPublisherController {
    @Autowired
    AdminPublisherService adminPublisherService;
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public BaseResult getSelectData(){
        List<PublisherSelectVo> list = adminPublisherService.getSelectData();
        return Results.successData(list);
    }
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResult indexGet(){
        Page<PublisherVo> list = adminPublisherService.getPageData();
        return Results.successMybatisPageData(list);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public BaseResult idGet(@PathVariable Integer id){
        PublisherDto list = adminPublisherService.getInfo(id);
        return Results.successData(list);
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResult indexPost(@RequestBody PublisherDto dto){
        adminPublisherService.addPublisher(dto);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public BaseResult idPut(@PathVariable Integer id, @RequestBody PublisherDto dto){
        adminPublisherService.updatePublisher(id,dto);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public BaseResult idPut(@PathVariable Integer id){
        adminPublisherService.deletePublisher(id);
        return Results.success();
    }
}
