package com.blackgaryc.library.tools;

import com.blackgaryc.library.domain.book.MqBookCollectorData;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.service.MinioClientService;
import com.rabbitmq.client.Channel;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RabbitListener(queues = {"book_data_collector_queue"},ackMode = "MANUAL")
public class MqConsumer {
    @Resource
    BookService bookService;
    @Resource
    MinioClientService minioClientService;

    @RabbitHandler
    public void receive(@Payload MqBookCollectorData data, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        System.out.println("data = " + data.getObject());
        Iterable<Result<Item>> results = minioClientService.listObjects(data.getObject());
        Result<Item> next = results.iterator().next();
        String etag = next.get().etag();

        channel.basicAck(deliveryTag,true);
    }
}
