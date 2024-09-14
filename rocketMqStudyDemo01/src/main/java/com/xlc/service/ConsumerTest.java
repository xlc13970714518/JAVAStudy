package com.xlc.service;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

//消息发送者
public class ConsumerTest {

    public static void main(String[] args) {
        try {
            // 实例化消息生产者Producer
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer ("consumergroup");

            // 设置NameServer的地址
            consumer.setNamesrvAddr("127.0.0.1:9876");

            //从最开始的位置开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
            //和发送者保持一致才能搜到消息
            consumer.subscribe("topic_log", "tags_error");
            consumer.subscribe("topicTest", "tag");

            // 注册回调实现类来处理从broker拉取回来的消息
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {

                    System.out.printf("%s 成功搜到消息: %s %n", Thread.currentThread().getName(), msgs);

                    // 标记该消息已经被成功消费
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            // 启动Producer实例
            consumer.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
