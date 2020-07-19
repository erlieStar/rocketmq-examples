package com.javashitang.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void syncSend() {
        SendResult sendResult = rocketMQTemplate.syncSend("syncTopic:tag1" ,"hello world");
        System.out.printf("syncSend sendResult=%s %n", sendResult);
    }

    @Test
    public void asyncSend() {
        rocketMQTemplate.asyncSend("asyncTopic", "hello world", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("asyncSend onSuccess sendResult=%s %n", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.printf("asyncSend onException throwable=%s %n", throwable);
            }
        });
    }
}
