package com.javashitang.rocketmq.chapter_1_syncSend;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {

    public static final String RPODUCER_GROUP_NAME = "syncProducerGroup";
    public static final String TOPIC_NAME = "syncTopic";
    public static final String TAG_NAME = "syncTag";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(RPODUCER_GROUP_NAME);
        // 设置重试次数为5次
        producer.setRetryTimesWhenSendFailed(5);
        producer.setRetryTimesWhenSendAsyncFailed(5);
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message message = new Message(TOPIC_NAME, TAG_NAME, ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
