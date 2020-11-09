package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @param queueName 延时队列名称
     * @param msg       消息内容
     * @param delayTime 延时时间 单位毫秒
     */
    public void sendMsg(String queueName, String msg, Integer delayTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:" + sdf.format(new Date()));
        rabbitTemplate.convertAndSend("test_exchange", queueName, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", delayTime);
                return message;
            }
        });
    }
}
