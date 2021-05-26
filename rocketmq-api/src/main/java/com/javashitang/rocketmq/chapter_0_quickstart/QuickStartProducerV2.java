package com.javashitang.rocketmq.chapter_0_quickstart;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.UUID;

@Slf4j
public class QuickStartProducerV2 {

    public static final String PRODUCER_GROUP_NAME = "quickStartProducerGroup";
    public static final String TOPIC_NAME = "quickStartTopic";
    public static final String TAG_NAME = "tagA";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        producer.setNamesrvAddr("myhost:9876");
        producer.setSendMsgTimeout(6000);
        producer.start();

        for (int i = 0; i < 10; i++) {
            String messageKey = UUID.randomUUID().toString();
            Message message = new Message(TOPIC_NAME, TAG_NAME, messageKey,
                    ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
