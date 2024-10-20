package com.xlc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudOpenFeignEmployeeApplication {

    public static void main(String[] args) {
        System.out.println("开始消费");
        SpringApplication.run(SpringCloudOpenFeignEmployeeApplication.class, args);
    }

}
