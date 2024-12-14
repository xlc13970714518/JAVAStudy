package com.xlc.spring_dynamicsources;

import com.xlc.spring_dynamicsources.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringDynamicSourcesApplicationTests {
    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.selectPage(1, 10));
    }

}
