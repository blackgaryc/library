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
import com.blackgaryc.library.tools.entity.BookUploadRequestEntityTool;
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
    BookUploadRequestService bookUploadRequestService;
    @Resource
    ProcessorFactory processorFactory;
    @Resource
    FileService fileService;
    @Resource
    BookDetailService bookDetailService;

    //??????s3?????????????????????????????????
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
                message = "????????????";
                exception.printStackTrace();
            }
            BookUploadRequestEntity entity = BookUploadRequestEntityTool.getBy(record);
            if (null == processBaseResult) {
                String msg = "??????????????????????????????";
                message = Objects.nonNull(message) ? msg + "," + message : msg;
            } else {
                entity.setMd5(processBaseResult.getMd5());
            }

            //?????????????????????????????????????????????md5?????????????????????
            boolean exists = bookUploadRequestService.lambdaQuery()
                    .eq(BookUploadRequestEntity::getMd5, entity.getMd5()).exists();
            if (exists) {
                // ??????????????????????????????md5?????????????????????????????????????????????????????????????????????????????????????????????
                entity.setStatus(BookUploadRequestStatusEnum.REFUSED.getCode());
                entity.setMessage("?????????????????????????????????");
                bookUploadRequestService.save(entity);
                channel.basicAck(deliveryTag, true);
                return;
            }
            //??????????????????????????????????????????base result???????????????????????????????????????????????????
            if (Strings.isNotEmpty(message) || processBaseResult==null) {
                entity.setMessage(message);
                entity.setStatus(BookUploadRequestStatusEnum.REFUSED.getCode());
                bookUploadRequestService.save(entity);
                channel.basicAck(deliveryTag, true);
                return;
            }
            //???????????????????????????
            bookUploadRequestService.save(entity);

            String fullUrl = "";
            if (null != processBaseResult.getThumbnail()) {
                //save thumbnail to minio as url
                Path path = FileTool.trans2localTempFile(processBaseResult.getThumbnail(), processBaseResult.getThumbnailExtension());
                //????????????????????????StpUtils?????????//????????????????????????????????????//???????????????
                ObjectWriteResponse objectWriteResponse = minioClientService.uploadObject(path.toFile(), ObjectKeyFactory.getInstance("book_cover_file_MD5_FILENAME", path.toFile(), path.getFileName().toString()));
                fullUrl = minioClientService.getFullUrl(objectWriteResponse.object());
            }

            //??????result???file??????
            Long uid = Long.valueOf(entity.getUid());
            FileEntity fileEntity = FileEntityTool.getBy(processBaseResult, uid);
            fileService.save(fileEntity);


            //??????book
            BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle(StringTools.splitFilename2NameAndExtension(entity.getFilename())[0]);
            bookEntity.setCreatedUid(uid);
            bookEntity.setThumbnail(fullUrl);
            bookService.save(bookEntity);

            //??????book???file
            BookDetailEntity bookDetailEntity = new BookDetailEntity();
            bookDetailEntity.setBookId(bookEntity.getId());
            bookDetailEntity.setFileId(fileEntity.getId());
            if (processBaseResult instanceof IFileProcessPageableResult pageableResult) {
                bookDetailEntity.setPage(pageableResult.getNumberOfPage());
            }
            bookDetailEntity.setSize(fileEntity.getSize());
            bookDetailService.save(bookDetailEntity);


            //??????request
            entity.setFileId(fileEntity.getId());
            entity.setBookId(bookEntity.getId());
            entity.setStatus(BookUploadRequestStatusEnum.WAIT_REVIEW.getCode());
            entity.setMessage("???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
            bookUploadRequestService.updateById(entity);
            //????????????
            channel.basicAck(deliveryTag, true);
            //?????????????????????????????????
        }
    }
}
