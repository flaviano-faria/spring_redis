package com.redis.infra.configuration;

import com.redis.domain.adapter.service.UserService;
import com.redis.domain.ports.interfaces.UserServicePort;
import com.redis.domain.ports.repository.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {


    @Bean
    UserServicePort userService(UserRepositoryPort userRepositoryPort) {
        return new UserService(userRepositoryPort);

    }

}
