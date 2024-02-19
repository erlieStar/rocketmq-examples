package com.javashitang.rocketmq.chapter_6_consumerGroup;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 一定要保证订阅关系一致，即一个consumerGroup下的topic和tag要完全一致
 * 不一致会导致消息丢失，消费逻辑混乱
 */
@Slf4j
public class GroupProducer {

    public static final String PRODUCER_GROUP_NAME = "testProducerGroup";
    public static final String TOPIC_NAME = "testGroupTopic";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        producer.setSendMsgTimeout(6000);
        producer.setNamesrvAddr("myhost:9876");
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
