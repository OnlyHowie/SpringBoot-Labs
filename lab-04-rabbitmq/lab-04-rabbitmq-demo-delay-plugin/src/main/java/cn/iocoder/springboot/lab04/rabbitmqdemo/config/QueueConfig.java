package cn.iocoder.springboot.lab04.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {
    /**
     * 延时队列交换机
     * 注意这里的交换机类型：CustomExchange
     *
     * @return
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("test_exchange", "x-delayed-message", true, false, args);
    }

    /**
     * 延时队列
     *
     * @return
     */
    @Bean
    public Queue queue() {
        Queue queue = new Queue("test_queue_1", true);
        return queue;
    }

    /**
     * 给延时队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(delayExchange()).with("test_queue_1").noargs();
    }
}
