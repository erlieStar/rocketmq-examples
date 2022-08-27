package com.javashitang.rocketmq.producer;

import com.javashitang.rocketmq.domain.Student;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void syncSendStr() {
        SendResult sendResult = rocketMQTemplate.syncSend("syncTopic:syncStrTag" ,"hello world");
        System.out.printf("syncSend sendResult=%s %n", sendResult);
    }

    @Test
    public void syncSendStudent() {
        Student student = new Student();
        student.setName("test");
        student.setAge(10);
        Message<Student> message = MessageBuilder.withPayload(student).build();
        rocketMQTemplate.syncSend("syncTopic:syncStudentTag", message);
        SendResult sendResult = rocketMQTemplate.syncSend("syncTopic:syncStudentTag" , message);
        System.out.printf("syncSend sendResult=%s %n", sendResult);
    }

    @Test
    public void asyncSend() {
        rocketMQTemplate.asyncSend("asyncTopic", "hello world", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("chapter_1_asyncSend onSuccess sendResult=%s %n", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.printf("chapter_1_asyncSend onException throwable=%s %n", throwable);
            }
        });
    }
}
