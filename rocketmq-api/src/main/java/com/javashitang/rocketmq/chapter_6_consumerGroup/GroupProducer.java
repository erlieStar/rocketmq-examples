package com.javashitang.rocketmq.chapter_6_consumerGroup;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

@Slf4j
public class GroupProducer {

    public static final String RPODUCER_GROUP_NAME = "testProducerGroup";
    public static final String TOPIC_NAME = "testGroupTopic";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(RPODUCER_GROUP_NAME);
        producer.setNamesrvAddr("www.javashitang.com:9876");
        producer.start();

        String[] tags = new String[] {"TagA", "TagB"};

        for (int i = 0; i < 6; i++) {
            Message message = new Message(TOPIC_NAME, tags[i % tags.length], ("hello rocketmq " + tags[i % tags.length]).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
