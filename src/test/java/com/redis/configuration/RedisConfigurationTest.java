package com.redis.configuration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
public class RedisConfigurationTest {

    private static GenericContainer<?> redis;

    @Before
    public void beforeAll() {
        redis = new GenericContainer<>(DockerImageName.parse("redis:7.0-alpine")).withExposedPorts(6379);
        redis.start();
        System.setProperty("spring.data.redis.host", redis.getHost());
        System.setProperty("spring.data.redis.port", redis.getMappedPort(6379).toString());


    }

    @Test
    public void redisRunningTest(){
        assert(redis.isRunning());
    }

}
