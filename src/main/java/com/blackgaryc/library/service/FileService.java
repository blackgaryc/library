package com.blackgaryc.library.service;

import com.blackgaryc.library.entity.FileEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author alex
* @description 针对表【file】的数据库操作Service
* @createDate 2023-02-23 15:34:04
*/
public interface FileService extends IService<FileEntity> {
    FileEntity findByMd5AndObjectKey(String md5,String objectKey);

    Boolean existByMd5(String md5);
}
