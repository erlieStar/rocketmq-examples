package com.javashitang.rocketmq.consumer;

import com.javashitang.rocketmq.domain.Student;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * selectorExpression 用来指定过滤的方式，tag和sql92
 * 用SelectorType来指定到底是tag还是sql92
 */
@Component
@RocketMQMessageListener(topic = "syncTopic", consumerGroup = "syncStudentGroup", selectorExpression = "syncStudentTag")
public class StudentConsumer implements RocketMQListener<Student> {

    @Override
    public void onMessage(Student message) {
        System.out.printf("StudentConsumer received: %s \n", message);
    }
}
