package com.xlc;

import com.xlc.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class RocketMqStudyDemo01ApplicationTests {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Test
    void contextLoads() {
        /**同步发送消息
         可靠的同步传输应用于广泛的场景，如重要通知消息、短信通知、短信营销系统等。*/
        // 发送字符串
        rocketMQTemplate.syncSend("springTopic", "Hello, World!");
        // 同步发送
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        // 发送对象
        rocketMQTemplate.syncSend("userTopic", User.builder().userAge(18).userName("Kitty").build());
        // 发送spring 消息
        rocketMQTemplate.syncSend("userTopic", MessageBuilder.withPayload(
                User.builder().userAge(21).userName("Lester")).setHeader(MessageHeaders.CONTENT_TYPE,
                MimeTypeUtils.APPLICATION_JSON_VALUE).build());
        /**异步发送消息
        异步传输一般用于响应时间敏感的业务场景。*/
        rocketMQTemplate.asyncSend("orderPaidTopic", "异步发送", new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
               // 成功回调
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }
            @Override
            public void onException(Throwable var1) {
            // 失败回调
                System.out.printf("async onException Throwable=%s %n", var1);
            }
        });
    }
    @Test
    public void testProducer(){
        /**异步发送消息
        异步传输一般用于响应时间敏感的业务场景。*/
        rocketMQTemplate.asyncSend("orderPaidTopic", "异步发送", new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                // 成功回调
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }
            @Override
            public void onException(Throwable var1) {
            // 失败回调
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });

        /**单向发送消息
        单向传输用于需要中等可靠性的情况，例如日志收集。*/

        rocketMQTemplate.sendOneWay("springTopic", "Hello, World");
        // 发送有序消息
        rocketMQTemplate.syncSendOrderly("orderly_topic",MessageBuilder.withPayload("Hello, World").build(),"hashkey");
        // 发送事务消息
        Message msg = MessageBuilder.withPayload("rocketMQTemplate transactional message ").build();

        // 第一个参数必须与@RocketMQTransactionListener的成员字段’transName’相同
        rocketMQTemplate.sendMessageInTransaction("test-topic", msg, null);
        // 使用注解@RocketMQTransactionListener定义事务监听器
        // 发送特殊标签（tag）消息
        rocketMQTemplate.convertAndSend("msgExtTopic" + ":tag0", "I’m from tag0"); // tag0 不是消费者选择的，可以通过tag过滤掉
        rocketMQTemplate.convertAndSend("msgExtTopic" + ":tag1", "I’m from tag1");
        // 发送批量消息
        List msgs = new ArrayList();
        for (int i = 0; i < 10; i++) {
            msgs.add(MessageBuilder.withPayload("Hello RocketMQ Batch Msg#" + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }
        SendResult sr = rocketMQTemplate.syncSend("springTopic", msgs, 60000);
    }
}
