package com.javashitang.rocketmq.chapter_4_classFilter;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

@Slf4j
public class ClassFilterProducer {

    public static final String RPODUCER_GROUP_NAME = "classFilterProducerGroup";
    public static final String TOPIC_NAME = "classFilterTopic";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(RPODUCER_GROUP_NAME);
        producer.start();

        for (int i = 0; i < 60; i++) {
            Message message = new Message(TOPIC_NAME, ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.putUserProperty("sourceId", String.valueOf(i));
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
