package com.javashitang.rocketmq.chapter_3_delayMsg;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

@Slf4j
public class DelayMessageProducer {

    public static final String PRODUCER_GROUP_NAME = "delayProducerGroup";
    public static final String TOPIC_NAME = "delayTopic";
    public static final String TAG_NAME = "delayTag";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        producer.setNamesrvAddr("myhost:9876");
        producer.start();

        for (int i = 0; i < 3; i++) {
            Message message = new Message(TOPIC_NAME, TAG_NAME, ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 设置消息延迟级别为2，延时5s左右
            message.setDelayTimeLevel(2);
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
