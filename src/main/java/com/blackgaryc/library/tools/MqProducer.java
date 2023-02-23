package com.blackgaryc.library.tools;

import com.blackgaryc.library.domain.book.MqBookCollectorData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;


@Component
public class MqProducer {
    private static final String topicExchangeName="library-common-exchange-book";
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    ObjectMapper objectMapper;

    public void sendUserUploadBookMessage(MqBookCollectorData data) throws JsonProcessingException {
        //发送到文件信息提取队列
        rabbitTemplate.convertAndSend(topicExchangeName,"book_data_collector",data);
    }
}
