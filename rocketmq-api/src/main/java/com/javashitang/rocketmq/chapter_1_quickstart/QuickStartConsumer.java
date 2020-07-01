package com.javashitang.rocketmq.chapter_1_quickstart;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

@Slf4j
public class QuickStartConsumer {

    public static final String CONSUMER_GROUP_NAME = "quickStartConsumerGroup";

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP_NAME);
        consumer.setConsumeFromWhere();

        consumer.start();
        System.out.println("Consumer Started");
    }
}
