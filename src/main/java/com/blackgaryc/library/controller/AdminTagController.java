package com.blackgaryc.library.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.admin.tag.TagAddDto;
import com.blackgaryc.library.domain.admin.tag.TagSelectVo;
import com.blackgaryc.library.entity.TagEntity;
import com.blackgaryc.library.myservice.AdminTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/tag")
public class AdminTagController {
    @Autowired
    AdminTagService adminTagService;
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public BaseResult getSelectData(){
        List<TagSelectVo> list = adminTagService.getSelectData();
        return Results.successData(list);
    }
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResult getList(){
        Page<TagSelectVo> list = adminTagService.getPageData();
        return Results.successMybatisPageData(list);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResult indexAdd(@RequestBody TagAddDto dto){
        adminTagService.addTag(dto);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public BaseResult indexDelete(@PathVariable Integer id){
        adminTagService.deleteTag(id);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public BaseResult indexUpdate(@PathVariable Integer id, @RequestBody TagAddDto dto){
        adminTagService.updateTag(id,dto);
        return Results.success();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public BaseResult indexUpdate(@PathVariable Integer id){
        TagEntity vo = adminTagService.getInfo(id);
        return Results.successData(vo);
    }
}
