package cn.iocoder.springboot.lab04.rabbitmqdemo.producer;

import cn.iocoder.springboot.lab04.rabbitmqdemo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RabbitmqApplicationTests {

    @Autowired
    private MessageServiceImpl messageService;

    @Test
    public void send() throws InterruptedException {

        for (int i = 5; i > 0; i--) {
            Integer delayTime = 1000 * i;
            messageService.sendMsg("test_queue_1", "hello i am delay msg, i=" + i, delayTime);
        }


        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
