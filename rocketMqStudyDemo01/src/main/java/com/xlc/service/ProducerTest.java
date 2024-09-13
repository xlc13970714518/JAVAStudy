package com.xlc.service;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

//消息发送者
public class ProducerTest {

    public static void main(String[] args) {
        try {
            // 实例化消息生产者Producer
            DefaultMQProducer producer = new DefaultMQProducer("producergroup");
            // 设置NameServer的地址
            producer.setNamesrvAddr("localhost:9876");
            // 启动Producer实例
            producer.start();
            for (int i = 0; i < 100; i++) {
                //构建消息
                Message message = new Message("topic_log","tags_error",("我是消息"+i).getBytes());
                SendResult sendResult = producer.send(message);
                System.out.printf("%s%n", sendResult);
            }

            // 如果不再发送消息，关闭Producer实例。
            producer.shutdown();

            testProductMessage();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public static void testProductMessage() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("producegrop");
        // 设置NameServer的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 启动producer实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 构建消息
            Message message = new Message("topicTest","tag",("我是xlc的消息" + i).toString().getBytes());
            SendResult sendResult = producer.send(message);
            System.out.printf("%s%n",sendResult);
            System.out.println(sendResult);

        }
        // 如果不再发送消息，关闭producer实例
        producer.shutdown();
    }
}

