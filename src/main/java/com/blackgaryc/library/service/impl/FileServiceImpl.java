package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.mapper.FileMapper;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【file】的数据库操作Service实现
* @createDate 2023-01-27 12:35:59
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity>
    implements FileService{
    @Override
    public FileEntity findByMd5AndObjectKey(String md5, String objectKey) {
        return baseMapper.findByMd5AndObject(md5, objectKey);
    }

    @Override
    public Boolean existByMd5(String md5) {
        return baseMapper.existByMd5(md5);
    }
}




