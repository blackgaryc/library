package com.blackgaryc.library.core.minio;


import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.error.FileAlreadyExistException;
import com.blackgaryc.library.core.error.FileNotAllowedToUploadException;
import com.blackgaryc.library.core.minio.objectkeys.UserBookFileKey;
import com.blackgaryc.library.core.minio.response.UserUploadBookResponse;
import com.blackgaryc.library.domain.book.MqBookCollectorData;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.service.MinioClientService;
import com.blackgaryc.library.tools.MqProducer;
import io.minio.ObjectWriteResponse;
import org.apache.tika.Tika;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.file.Paths;

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
            Boolean fileExisted = fileService.existByMd5(objectWriteResponse.etag());
            if (fileExisted){
                throw new FileAlreadyExistException(Paths.get(objectKey.getKey()).getFileName().toString());
            }
            //生成一个文件id
            BookEntity entity = new BookEntity();
            entity.setTitle(Paths.get(objectKey.getKey()).getFileName().toString());
            entity.setCreatedUid(StpUtil.getLoginIdAsLong());
            boolean save = bookService.save(entity);
            if (!save){
                throw new RuntimeException("save failed");
            }
            //提交到mq
            //异步处理 文件信息 并根据id更新数据（mq中）后期可以拆分为独立的组件进行部署
            mqProducer.sendUserUploadBookMessage(
                    new MqBookCollectorData(
                            entity.getId(),
                            StpUtil.getLoginIdAsLong(),
                            objectKey.getKey(),
                            objectWriteResponse.etag()));
            //返回bookId
            //用户可以根据文件id去访问图书详情页面，图书详情在审核完成前仅为用户与审核员可见
            return new UserUploadBookResponse(entity.getId());
        };
    }
}
