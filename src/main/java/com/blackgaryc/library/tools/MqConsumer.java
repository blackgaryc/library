package com.blackgaryc.library.tools;

import com.blackgaryc.library.core.error.FileProcessorErrorException;
import com.blackgaryc.library.core.error.FileProcessorNotSupportException;
import com.blackgaryc.library.core.error.MinioObjectKeyGenerateException;
import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.core.file.processor.IFileProcessPageableResult;
import com.blackgaryc.library.core.file.processor.MinioFileInfo;
import com.blackgaryc.library.core.file.processor.ProcessorFactory;
import com.blackgaryc.library.core.minio.ObjectKeyFactory;
import com.blackgaryc.library.core.mq.resut.Record;
import com.blackgaryc.library.core.mq.resut.S3Notify;
import com.blackgaryc.library.entity.*;
import com.blackgaryc.library.myservice.MinioClientService;
import com.blackgaryc.library.service.*;
import com.blackgaryc.library.tools.entity.LogFileUploadEntityTool;
import com.blackgaryc.library.tools.entity.FileEntityTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rometools.utils.Strings;
import io.minio.*;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
@RabbitListener(queues = {"book_data_collector_queue"}, ackMode = "MANUAL")
public class MqConsumer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    BookService bookService;
    @Resource
    MinioClientService minioClientService;

    //    @RabbitHandler
//    public void receive(@Payload MqBookCollectorData data,
//                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
//                        Channel channel) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
//        System.out.println("data = " + data.getObject());
//        Iterable<Result<Item>> results = minioClientService.listObjects(data.getObject());
//        Result<Item> next = results.iterator().next();
//        String etag = next.get().etag();
//        channel.basicAck(deliveryTag,false);
//    }
    @Resource
    ObjectMapper objectMapper;

    @Resource
    LogFileUploadService bookUploadRequestService;
    @Resource
    ProcessorFactory processorFactory;
    @Resource
    FileService fileService;
    @Resource
    BookDetailService bookDetailService;

    //处理s3用户上传图书的通知消息
    @RabbitHandler
    public void process(@Payload byte[] data,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                        Channel channel) throws IOException, MinioObjectKeyGenerateException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        S3Notify s3Notify = objectMapper.readValue(data, S3Notify.class);
        log.info(objectMapper.writeValueAsString(s3Notify));
        for (Record record : s3Notify.getRecords()) {
            IFileProcessBaseResult processBaseResult = null;
            String message = null;
            try {
                processBaseResult = processorFactory.process(new MinioFileInfo(record));
            } catch (FileProcessorNotSupportException e) {
                message = e.getMessage();
            } catch (FileProcessorErrorException e) {
                message = e.getMessage();
                processBaseResult = e.getBaseResult();
            } catch (Throwable exception) {
                message = "未知错误";
                exception.printStackTrace();
            }
            LogFileUploadEntity entity = LogFileUploadEntityTool.getBy(record);
            if (null == processBaseResult) {
                String msg = "无法获得文件处理结果";
                message = Objects.nonNull(message) ? msg + "," + message : msg;
            } else {
                entity.setMd5(processBaseResult.getMd5());
            }

            //尝试检查文件是否被上传过。通常md5是不会为空的。
            boolean exists = bookUploadRequestService.lambdaQuery()
                    .eq(LogFileUploadEntity::getMd5, entity.getMd5()).exists();
            if (exists) {
                // 保存之前如果存在相同md5的数据，则说明已经被上传过，这样同一本被拒绝的书不会被多次审核
                entity.setStatus(BookUploadRequestStatusEnum.REFUSED.getCode());
                entity.setMessage("该文件在过去曾被上传过");
                bookUploadRequestService.save(entity);
                channel.basicAck(deliveryTag, true);
                return;
            }
            //如果捕获到了异常或者无法读取base result，则拒绝上传请求，并设置相应的消息
            if (Strings.isNotEmpty(message) || processBaseResult==null) {
                entity.setMessage(message);
                entity.setStatus(BookUploadRequestStatusEnum.REFUSED.getCode());
                bookUploadRequestService.save(entity);
                channel.basicAck(deliveryTag, true);
                return;
            }
            //未遇到错误时的保存
            bookUploadRequestService.save(entity);

            String fullUrl = "";
            if (null != processBaseResult.getThumbnail()) {
                //save thumbnail to minio as url
                Path path = FileTool.trans2localTempFile(processBaseResult.getThumbnail(), processBaseResult.getThumbnailExtension());
                //千万别调用到使用StpUtils的代码//当前环境无法读取用户信息//必然会失败
                ObjectWriteResponse objectWriteResponse = minioClientService.uploadObject(path.toFile(), ObjectKeyFactory.getInstance("book_cover_file_MD5_FILENAME", path.toFile(), path.getFileName().toString()));
                fullUrl = minioClientService.getFullUrl(objectWriteResponse.object());
            }

            //保存result到file表中
            Long uid = Long.valueOf(entity.getUid());
            FileEntity fileEntity = FileEntityTool.getBy(processBaseResult, uid);
            fileService.save(fileEntity);


            //创建book
            BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle(StringTools.splitFilename2NameAndExtension(entity.getFilename())[0]);
            bookEntity.setCreatedUid(uid);
            bookEntity.setThumbnail(fullUrl);
            bookService.save(bookEntity);

            //绑定book和file
            BookDetailEntity bookDetailEntity = new BookDetailEntity();
            bookDetailEntity.setBookId(bookEntity.getId());
            bookDetailEntity.setFileId(fileEntity.getId());
            if (processBaseResult instanceof IFileProcessPageableResult pageableResult) {
                bookDetailEntity.setPage(pageableResult.getNumberOfPage());
            }
            bookDetailEntity.setSize(fileEntity.getSize());
            bookDetailService.save(bookDetailEntity);


            //更新request
            entity.setFileId(fileEntity.getId());
            entity.setBookId(bookEntity.getId());
            entity.setStatus(BookUploadRequestStatusEnum.WAIT_REVIEW.getCode());
            entity.setMessage("处理完成，在审核完成之前您可以通过点击上传的书籍，并在页面内编辑完善更多书籍信息。");
            bookUploadRequestService.updateById(entity);
            //确认消息
            channel.basicAck(deliveryTag, true);
            //后续将由管理员手动审核
        }
    }
}
