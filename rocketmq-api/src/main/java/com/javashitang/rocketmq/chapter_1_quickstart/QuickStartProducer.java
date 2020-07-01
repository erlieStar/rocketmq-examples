package com.javashitang.rocketmq.chapter_1_quickstart;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

@Slf4j
public class QuickStartProducer {

    public static final String RPODUCER_GROUP_NAME = "quickStartProducerGroup";
    public static final String TOPIC_NAME = "testTopic";
    public static final String TAG_NAME = "testTag";

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("unique_group_name");
        producer.start();

        for (int i = 0; i < 100; i++) {
            try {
                Message message = new Message(TOPIC_NAME, TAG_NAME, ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        producer.shutdown();
    }
}
