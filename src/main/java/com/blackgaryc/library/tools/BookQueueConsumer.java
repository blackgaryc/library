package com.blackgaryc.library.tools;

import com.blackgaryc.library.core.error.FileProcessorNotSupportException;
import com.blackgaryc.library.core.file.processor.IFileProcessBaseResult;
import com.blackgaryc.library.core.file.processor.IFileProcessPageableResult;
import com.blackgaryc.library.core.file.processor.MinioFileInfo;
import com.blackgaryc.library.core.file.processor.ProcessorFactory;
import com.blackgaryc.library.core.mq.resut.Record;
import com.blackgaryc.library.core.mq.resut.S3Notify;
import com.blackgaryc.library.service.IMQBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BookQueueConsumer {
    private final Logger log= LoggerFactory.getLogger(this.getClass());
    @Resource
    IMQBookService imqBookService;
    @Resource
    ObjectMapper objectMapper;
    @Resource
    ProcessorFactory processorFactory;

    @RabbitListener(queues = {"${queue.name}"},ackMode="NONE")
    public void receive(@Payload String body) {
        log.info(body);
        try {
            S3Notify s3Notify = objectMapper.readValue(body, S3Notify.class);
            for (Record record : s3Notify.getRecords()) {
                //process by book media handler
                IFileProcessBaseResult baseResult = processorFactory.process(new MinioFileInfo(record));
                imqBookService.save(baseResult);
            }

        } catch (JsonProcessingException | FileProcessorNotSupportException e) {
            throw new RuntimeException(e);
        }
    }
}