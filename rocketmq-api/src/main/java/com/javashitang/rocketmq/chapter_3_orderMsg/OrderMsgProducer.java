package com.javashitang.rocketmq.chapter_3_orderMsg;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * 针对一个订单的不同状态的变更消息发送到同一个消息队列上
 */
@Slf4j
public class OrderMsgProducer {

    public static final String PRODUCER_GROUP_NAME = "orderMsgProducerGroup";
    public static final String TOPIC_NAME = "orderMsgTopic";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        producer.start();

        String[] tags = new String[] {"TagA", "TageB", "TagC", "TageD"};
        for (int i = 0; i < 5; i++) {
            int orderId = i % 10;
            Message message = new Message(TOPIC_NAME, tags[i % tags.length], ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                /**
                 * @param mqs topic对应的message queue
                 * @param msg send方法传入的message
                 * @param arg send方法传入的orderId
                 */
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    // 根据业务对象选择对应的队列
                    Integer orderId = (Integer) arg;
                    int index = orderId % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
