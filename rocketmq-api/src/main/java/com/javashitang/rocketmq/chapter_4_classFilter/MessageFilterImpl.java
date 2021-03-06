package com.javashitang.rocketmq.chapter_4_classFilter;

import org.apache.rocketmq.common.filter.FilterContext;
import org.apache.rocketmq.common.filter.MessageFilter;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author lilimin
 * @since 2021-04-18
 */
public class MessageFilterImpl implements MessageFilter {

    @Override
    public boolean match(MessageExt msg, FilterContext context) {
        String sourceId = msg.getProperty("sourceId");
        if (sourceId != null && ((Integer.valueOf(sourceId) & 1) == 1)) {
            return true;
        }
        return false;
    }
}
