package com.xlc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudOpenFeignDeptApplication {


    public static void main(String[] args) {
        System.out.println("注册dept服务");
        SpringApplication.run(SpringCloudOpenFeignDeptApplication.class, args);
    }

}
