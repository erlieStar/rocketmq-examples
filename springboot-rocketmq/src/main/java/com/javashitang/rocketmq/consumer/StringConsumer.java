package com.javashitang.rocketmq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * selectorExpression 用来指定过滤的方式，tag和sql92
 * 用SelectorType来指定到底是tag还是sql92
 */
@Service
@RocketMQMessageListener(topic = "", consumerGroup = "", selectorExpression = "*")
public class StringConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.printf("StringConsumer received: %s \n", message);
    }
}
