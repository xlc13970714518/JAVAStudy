package com.xlc.spring_dynamicsources;

import com.xlc.spring_dynamicsources.service.OrderService;
import com.xlc.spring_dynamicsources.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.xlc.spring_dynamicsources.Dao")
public class SpringDynamicSourcesApplication {

    public static void main(String[] args) {

        //SpringApplication.run(SpringDynamicSourcesApplication.class, args);
        ApplicationContext context = SpringApplication.run(SpringDynamicSourcesApplication.class, args);
        OrderService orderService = context.getBean(OrderService.class);
        UserService userService = context.getBean(UserService.class);
        System.out.println(orderService.selectByPage(1, 5));
        System.out.println(userService.selectPage(1, 5));

    }

}
