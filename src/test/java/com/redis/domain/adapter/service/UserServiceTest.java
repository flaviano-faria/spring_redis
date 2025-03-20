package com.redis.domain.adapter.service;

import com.redis.app.SpringRedisApplication;
import com.redis.domain.User;
import com.redis.domain.UserDTO;
import com.redis.domain.ports.repository.UserRepositoryPort;
import com.redis.infra.adapters.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringRedisApplication.class)
@Testcontainers
@ComponentScan(basePackages = {
    "com.redis.domain.adapter.service",
    "com.redis.infra.adapters.repository",
    "com.redis.infra.configuration",
    "com.redis.controller",
    "com.redis.exception"
})
public class UserServiceTest {

    @Container
    static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:7.0-alpine"))
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redis::getHost);
        registry.add("spring.data.redis.port", () -> redis.getMappedPort(6379).toString());
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    void createUserTest() {
        // Given
        UserDTO userDTO = new UserDTO("1234", "user1234");

        // When
        userService.createUser(userDTO);

        // Then
        UserDTO foundUser = userService.findUserById("1234");
        assertNotNull(foundUser);
        assertEquals("1234", foundUser.getId());
        assertEquals("user1234", foundUser.getName());
    }

    @Test
    void getAllUsersTest() {
        // Given
        UserDTO user1 = new UserDTO("1", "user1");
        UserDTO user2 = new UserDTO("2", "user2");
        userService.createUser(user1);
        userService.createUser(user2);

        // When
        List<UserDTO> users = userService.getAllUsers();

        // Then
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getId().equals("1") && u.getName().equals("user1")));
        assertTrue(users.stream().anyMatch(u -> u.getId().equals("2") && u.getName().equals("user2")));
    }

    @Test
    void findUserByIdTest() {
        // Given
        UserDTO userDTO = new UserDTO("1234", "user1234");
        userService.createUser(userDTO);

        // When
        UserDTO foundUser = userService.findUserById("1234");

        // Then
        assertNotNull(foundUser);
        assertEquals("1234", foundUser.getId());
        assertEquals("user1234", foundUser.getName());
    }

    @Test
    void findUserByIdNotFoundTest() {
        // When/Then
        assertThrows(RuntimeException.class, () -> userService.findUserById("nonexistent"));
    }
}
