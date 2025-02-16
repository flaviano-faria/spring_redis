package com.redis.domain.adapter.service;

import com.redis.domain.User;
import com.redis.domain.UserDTO;
import com.redis.domain.ports.interfaces.UserServicePort;
import com.redis.infra.adapters.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class UserServiceTest {

    private static GenericContainer<?> redis;




    @Before
    public void beforeAll() {
        redis = new GenericContainer<>(DockerImageName.parse("redis:7.0-alpine")).withExposedPorts(6379);
        redis.start();
        System.setProperty("spring.data.redis.host", redis.getHost());
        System.setProperty("spring.data.redis.port", redis.getMappedPort(6379).toString());

    }



    @Test
    public void createUserTest(){
        UserService userService = mock(UserService.class);
        UserDTO userDTO = new UserDTO("1234", "user1234");
        userService.createUser(userDTO);
        assert  userDTO.getId() != null;


    }
}
