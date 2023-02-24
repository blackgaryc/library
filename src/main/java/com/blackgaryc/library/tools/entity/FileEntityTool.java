package com.blackgaryc.library.tools.entity;

import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.entity.FileEntity;

public class FileEntityTool {
    public static FileEntity getBy(IFileProcessBaseResult result,Long uid){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(result.getFilename());
        fileEntity.setExtension(result.getExtension());
        fileEntity.setSize(result.getSize());
        fileEntity.setMd5(result.getMd5());
        fileEntity.setMimetype(result.getMimetype());
        fileEntity.setObject(result.getObjectKey());
        fileEntity.setUid(uid);
        return fileEntity;
    }
}
