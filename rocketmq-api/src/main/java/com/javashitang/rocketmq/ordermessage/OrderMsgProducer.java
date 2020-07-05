package com.javashitang.rocketmq.ordermessage;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

@Slf4j
public class OrderMsgProducer {

    public static final String RPODUCER_GROUP_NAME = "quickStartProducerGroup";
    public static final String TOPIC_NAME = "testTopic";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(RPODUCER_GROUP_NAME);
        producer.start();

        String[] tags = new String[] {"TagA", "TageB", "TagC", "TageD"};
        for (int i = 0; i < 100; i++) {
            int orderId = i % 100;
            Message message = new Message(TOPIC_NAME, tags[i % tags.length], ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
