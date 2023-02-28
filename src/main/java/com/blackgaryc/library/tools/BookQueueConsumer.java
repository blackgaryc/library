package com.blackgaryc.library.tools;

import com.blackgaryc.library.core.elasticsearch.domain.Book;
import com.blackgaryc.library.core.elasticsearch.repository.BookRepository;
import com.blackgaryc.library.core.error.FileProcessorErrorException;
import com.blackgaryc.library.core.error.FileProcessorNotSupportException;
import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.core.file.processor.MinioFileInfo;
import com.blackgaryc.library.core.file.processor.ProcessorFactory;
import com.blackgaryc.library.core.minio.MinioProperty;
import com.blackgaryc.library.core.mq.resut.Record;
import com.blackgaryc.library.core.mq.resut.S3Notify;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.myservice.IMQBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.UUID;

//@Component
public class BookQueueConsumer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    IMQBookService imqBookService;
    @Resource
    ObjectMapper objectMapper;
    @Resource
    ProcessorFactory processorFactory;

    @Resource
    FileService fileService;

    //    @Autowired
    MinioClient minioClient;
    //    @Autowired
    MinioProperty minioProperty;

    @RabbitListener(queues = {"${queue.name}"}, ackMode = "NONE")
    @Transactional
    public void processUserBookUpload(@Payload String body) {
        log.info(body);
        try {
            S3Notify s3Notify = objectMapper.readValue(body, S3Notify.class);
            for (Record record : s3Notify.getRecords()) {
                String md5 = record.getS3().getObject().getETag();
                Boolean exist = fileService.lambdaQuery().eq(FileEntity::getMd5, md5).exists();
                String userFileUploadKey = URLDecoder.decode(record.getS3().getObject().getKey(), Charset.defaultCharset());
                String bucket = minioProperty.getBucket();
                if (exist) {
                    log.warn("file " + userFileUploadKey + " md5[" + md5 + "] exist");
                } else {
                    //process by book media handler
                    IFileProcessBaseResult baseResult = processorFactory.process(new MinioFileInfo(record));
                    imqBookService.save(baseResult);
                    // copy book from  user space to project space
                    String copySourceKey = baseResult.getObjectKey();
                    CopySource copySource = CopySource.builder().bucket(bucket).object(copySourceKey).build();
                    String projectObjectKey = "book/" + UUID.randomUUID() + "/" + Paths.get(copySourceKey).getFileName().toString();
                    FileEntity fileEntity = fileService.lambdaQuery()
                            .eq(FileEntity::getMd5, md5).
                            eq(FileEntity::getObject, copySourceKey)
                            .getEntity();
                    fileEntity.setObject(projectObjectKey);
                    // update db record
                    fileService.updateById(fileEntity);
                    // handle minio file move
                    try {
                        minioClient.copyObject(CopyObjectArgs.builder().bucket(bucket).source(copySource).object(projectObjectKey).build());
                        //when file is copied to project space, end transactional
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
                try {
                    minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(userFileUploadKey).build());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (JsonProcessingException | FileProcessorNotSupportException e) {
            throw new RuntimeException(e);
        } catch (FileProcessorErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @Resource
    BookRepository bookRepository;

    @RabbitListener(queues = {"${queue.es.book.add}"}, ackMode = "NONE")
    public void processProjectBookAdd2Es(@Payload String body) {
        log.info(body);
        try {
            S3Notify s3Notify = objectMapper.readValue(body, S3Notify.class);
            for (Record record : s3Notify.getRecords()) {
                log.info(record.toString());
                //load data form database
                Book book = imqBookService.findBookByMd5AndObjectKey(record.getS3().getObject().getETag(), URLDecoder.decode(record.getS3().getObject().getKey(), Charset.defaultCharset()));
                //push to elasticsearch
                bookRepository.save(book);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}