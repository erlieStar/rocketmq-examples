package com.javashitang.rocketmq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * selectorExpression 用来指定过滤的方式，tag和sql92
 * 用SelectorType来指定到底是tag还是sql92
 */
@Component
@RocketMQMessageListener(topic = "syncTopic", consumerGroup = "syncStrGroup", selectorExpression = "syncStrTag")
public class StringConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.printf("StringConsumer received: %s \n", message);
    }
}