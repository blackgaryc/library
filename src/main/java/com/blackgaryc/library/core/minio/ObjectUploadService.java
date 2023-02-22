package com.blackgaryc.library.core.minio;


import com.blackgaryc.library.core.minio.objectkeys.UserBookFileKey;
import com.blackgaryc.library.core.minio.response.UserUploadBookResponse;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.service.MinioClientService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ObjectUploadService implements InitializingBean {
    @Resource
    MinioClientService minioClientService;

    ObjectUploadStrategy defaultResponse;
    ObjectUploadStrategy userUploadBookResponse;


    public ObjectUploadStrategy getInstance(IObjectKey objectKey) {
        if (objectKey instanceof UserBookFileKey) {
            return userUploadBookResponse;
        }
        return defaultResponse;
    }

    @Resource
    FileService fileService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //upload file and return file url
        defaultResponse = (objectKey, tmpfile) -> {
            minioClientService.uploadObject(tmpfile, objectKey);
            return minioClientService.getFullUrl(objectKey.getKey());
        };


        //upload book file and save to database , then return file id
        userUploadBookResponse = (objectKey, tmpfile)-> {
            minioClientService.uploadObject(tmpfile, objectKey);
            return new UserUploadBookResponse(-1L, minioClientService.getFullUrl(objectKey.getKey()));
        };
    }
}
