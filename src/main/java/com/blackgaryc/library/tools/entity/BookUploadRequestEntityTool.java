package com.blackgaryc.library.tools.entity;

import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.core.mq.resut.MinioObject;
import com.blackgaryc.library.core.mq.resut.Record;
import com.blackgaryc.library.entity.BookUploadRequestEntity;
import com.blackgaryc.library.entity.BookUploadRequestStatusEnum;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class BookUploadRequestEntityTool {
    public static BookUploadRequestEntity getBy(Record record){
        BookUploadRequestEntity  entity= new BookUploadRequestEntity();
        MinioObject object = record.getS3().getObject();
        //etag not md5
        entity.setMd5("");
        entity.setObject(URLDecoder.decode(object.getKey(), StandardCharsets.UTF_8));
        entity.setUid(entity.getObject().split("/")[1]);
        entity.setFilename(Paths.get(entity.getObject()).getFileName().toString());
        entity.setMessage("请稍候，待文件处理完成后可以查看更多信息");
        entity.setStatus(BookUploadRequestStatusEnum.WAIT_PROCESS.getCode());
        return entity;
    }

    public static BookUploadRequestEntity getBy(IFileProcessBaseResult result,Record record) {
        BookUploadRequestEntity entity = getBy(record);
        entity.setMd5(result.getMd5());
        return entity;
    }
}
