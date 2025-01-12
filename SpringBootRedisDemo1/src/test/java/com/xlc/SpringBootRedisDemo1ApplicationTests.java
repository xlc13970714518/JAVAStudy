package com.xlc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringBootRedisDemo1ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
    }

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("testName", "学习Redis 天天学");
        String testName = (String) redisTemplate.opsForValue().get("testName");
        System.out.println(testName);
    }

}
