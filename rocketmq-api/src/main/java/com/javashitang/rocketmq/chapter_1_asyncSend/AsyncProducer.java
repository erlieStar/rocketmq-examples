package com.javashitang.rocketmq.chapter_1_asyncSend;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;

public class AsyncProducer {

    public static final String PRODUCER_GROUP_NAME = "quickStartProducerGroup";
    public static final String TOPIC_NAME = "testTopic";
    public static final String TAG_NAME = "testTag";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        producer.start();
        int megCount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(megCount);
        for (int i = 0; i < 100; i++) {
            int index = i;
            Message message = new Message(TOPIC_NAME, TAG_NAME, ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(message, new SendCallback() {

                // 发送成功回调 onSuccess
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%d send success %s %n", index, sendResult.getMsgId());
                }

                // 发送失败回调 onException
                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.printf("%d send failed %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await();
        producer.shutdown();
    }
}
