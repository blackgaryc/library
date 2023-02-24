package com.blackgaryc.library.tools;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.core.file.processor.MinioFileInfo;
import com.blackgaryc.library.core.file.processor.ProcessorFactory;
import com.blackgaryc.library.core.mq.resut.Record;
import com.blackgaryc.library.core.mq.resut.S3Notify;
import com.blackgaryc.library.domain.book.Book;
import com.blackgaryc.library.domain.book.MqBookCollectorData;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.BookUploadRequestEntity;
import com.blackgaryc.library.entity.BookUploadRequestStatusEnum;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.BookUploadRequestService;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.service.MinioClientService;
import com.blackgaryc.library.tools.entity.BookUploadRequestEntityTool;
import com.blackgaryc.library.tools.entity.FileEntityTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.RemoveObjectArgs;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

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
    BookUploadRequestService bookUploadRequestService;
    @Resource
    ProcessorFactory processorFactory;
    @Resource
    FileService fileService;

    //处理s3用户上传图书的通知消息
    @RabbitHandler
    public void process(@Payload byte[] data,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                        Channel channel) throws IOException {
        try {
            S3Notify s3Notify = objectMapper.readValue(data, S3Notify.class);
            for (Record record : s3Notify.getRecords()) {
                BookUploadRequestEntity entity = BookUploadRequestEntityTool.getBy(record);
                boolean exists = bookUploadRequestService.lambdaQuery()
                        .eq(BookUploadRequestEntity::getMd5, entity.getMd5()).exists();
                bookUploadRequestService.save(entity);
                if (exists) {
                    // 保存之前如果存在相同md5的数据，则说明已经被上传过，这样同一本被拒绝的书不会被多次审核
                    entity.setStatus(BookUploadRequestStatusEnum.REFUSED.getCode());
                    entity.setMessage("该文件已被上传");
                    bookUploadRequestService.updateById(entity);
                    return;
                }
                IFileProcessBaseResult result = processorFactory.process(new MinioFileInfo(record));
                //保存result到file表中
                Long uid = Long.valueOf(entity.getUid());
                FileEntity fileEntity = FileEntityTool.getBy(result, uid);
                fileService.save(fileEntity);

                //创建book
                BookEntity bookEntity = new BookEntity();
                bookEntity.setTitle(entity.getFilename());
                bookEntity.setCreatedUid(uid);
                bookService.save(bookEntity);

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
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicAck(deliveryTag, false);
            System.out.println("data = " + Arrays.toString(data));
        }
    }
}
