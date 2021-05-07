package com.javashitang.rocketmq.chapter_1_onewaySend;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OnewayProducer {

    public static final String RPODUCER_GROUP_NAME = "onewayProducerGroup";
    public static final String TOPIC_NAME = "onewayTopic";
    public static final String TAG_NAME = "onewayTag";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(RPODUCER_GROUP_NAME);
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message message = new Message(TOPIC_NAME, TAG_NAME, ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 给mq发送完消息，代码继续执行，不关注是否发送成功
            producer.sendOneway(message);
        }

        producer.shutdown();
    }
}
