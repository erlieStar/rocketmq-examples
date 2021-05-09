package com.javashitang.rocketmq.chapter_3_batchMsg;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量发送消息，需要满足如下几个条件
 * 1. 总大小不超过1MB
 * 2. 相同的topic
 * 3. 相同的waitStoreMsgOK
 * 4. 不能是延迟消息
 */
public class SimpleBatchMsgProducer {

    public static final String PRODUCER_GROUP_NAME = "batchProducerGroup";
    public static final String TOPIC_NAME = "testTopic";
    public static final String TAG_NAME = "tag";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        producer.start();
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(TOPIC_NAME, TAG_NAME, "id001", "hello world1".getBytes()));
        messageList.add(new Message(TOPIC_NAME, TAG_NAME, "id002", "hello world2".getBytes()));
        messageList.add(new Message(TOPIC_NAME, TAG_NAME, "id003", "hello world3".getBytes()));
        producer.send(messageList);
    }
}
