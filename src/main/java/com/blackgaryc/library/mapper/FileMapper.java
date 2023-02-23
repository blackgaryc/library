package com.blackgaryc.library.mapper;

import com.blackgaryc.library.entity.FileEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.annotations.Query;

/**
* @author alex
* @description 针对表【file】的数据库操作Mapper
* @createDate 2023-02-23 15:34:04
* @Entity com.blackgaryc.library.entity.FileEntity
*/
@Mapper
public interface FileMapper extends BaseMapper<FileEntity> {
    FileEntity findByMd5AndObject(String md5,String object);
    Boolean existByMd5(String md5);
}




