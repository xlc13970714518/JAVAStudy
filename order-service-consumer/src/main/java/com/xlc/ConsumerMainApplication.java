package com.xlc;

import com.xlc.gamallinterface.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerMainApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        OrderService orderService = applicationContext.getBean(OrderService.class);
        System.out.println(orderService);
        orderService.initOrder(1);
        System.out.println("调用完成");
    }
}