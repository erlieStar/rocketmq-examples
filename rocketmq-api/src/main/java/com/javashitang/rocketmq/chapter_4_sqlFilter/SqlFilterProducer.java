package com.javashitang.rocketmq.chapter_4_sqlFilter;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

@Slf4j
public class SqlFilterProducer {

    public static final String PRODUCER_GROUP_NAME = "sqlFilterProducerGroup";
    public static final String TOPIC_NAME = "sqlFilterTopic";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        producer.setNamesrvAddr("myhost:9876");
        producer.setSendMsgTimeout(6000);
        producer.start();

        String[] tags = new String[] {"TagA", "TagB", "TagC"};

        for (int i = 0; i < 12; i++) {
            Message message = new Message(TOPIC_NAME, tags[i % tags.length], ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.putUserProperty("a", String.valueOf(i));
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }
}
