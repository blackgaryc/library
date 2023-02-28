package com.blackgaryc.library.core.minio;


import com.blackgaryc.library.core.error.FileAlreadyExistException;
import com.blackgaryc.library.core.error.FileNotAllowedToUploadException;
import com.blackgaryc.library.core.minio.objectkeys.UserBookFileKey;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.myservice.MinioClientService;
import com.blackgaryc.library.tools.MqProducer;
import io.minio.ObjectWriteResponse;
import org.apache.tika.Tika;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.file.Paths;

@Component
public class ObjectUploadStrategyBean implements InitializingBean {
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
    @Resource
    BookService bookService;
    @Resource
    MqProducer mqProducer;
    @Resource
    Tika tika;

    @Override
    public void afterPropertiesSet() throws Exception {
        //upload file and return file url
        defaultResponse = (objectKey, tmpfile) -> {
            minioClientService.uploadObject(tmpfile, objectKey);
            return minioClientService.getFullUrl(objectKey.getKey());
        };

        //upload book file and save to database , then return file id
        userUploadBookResponse = (objectKey, tmpfile)-> {
            //check file mimetype is permitted or not
            String detect = tika.detect(tmpfile);
            if (!detect.equals("application/pdf")){
                throw new FileNotAllowedToUploadException(Paths.get(objectKey.getKey()).getFileName().toString());
            }
            //upload
            ObjectWriteResponse objectWriteResponse = minioClientService.uploadObject(tmpfile, objectKey);
            Boolean fileExisted = fileService.lambdaQuery().eq(FileEntity::getMd5,objectWriteResponse.etag()).exists();
            if (fileExisted){
                //delete file
                //throw exception
                throw new FileAlreadyExistException(Paths.get(objectKey.getKey()).getFileName().toString());
            }
            //上一步上传文件会触发s3notify
//            //异步处理 文件信息 并根据id更新数据（mq中）后期可以拆分为独立的组件进行部署
//            mqProducer.sendUserUploadBookMessage(
//                    new MqBookCollectorData(
//                            entity.getId(),
//                            StpUtil.getLoginIdAsLong(),
//                            objectKey.getKey(),
//                            objectWriteResponse.etag()));
            //返回object key
            //前端在上传完成后可以通过查询objectkey，准确查询上传的文件处理的结果
            //返回对应的信息，例如文件已经存在。
            return minioClientService.getFullUrl(objectKey.getKey());
        };
    }
}
