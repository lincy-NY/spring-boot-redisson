package xyz.lincy.springbootredission;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootRedissionApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void redisTest() {
        redissonClient.getBucket("hello").set("bug");
        String test = (String) redissonClient.getBucket("hello").get();
        System.out.println(test);

    }

}
