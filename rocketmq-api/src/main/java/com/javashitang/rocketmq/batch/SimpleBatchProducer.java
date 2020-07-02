package com.javashitang.rocketmq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

public class SimpleBatchProducer {

    public static final String RPODUCER_GROUP_NAME = "batchProducerGroup";
    public static final String TOPIC_NAME = "testTopic";
    public static final String TAG_NAME = "tag";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(RPODUCER_GROUP_NAME);
        producer.start();
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(TOPIC_NAME, TAG_NAME, "id001", "hello world1".getBytes()));
        messageList.add(new Message(TOPIC_NAME, TAG_NAME, "id002", "hello world2".getBytes()));
        messageList.add(new Message(TOPIC_NAME, TAG_NAME, "id003", "hello world3".getBytes()));
        producer.send(messageList);
    }
}
