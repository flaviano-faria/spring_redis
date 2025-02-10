package com.redis.app;

import com.redis.infra.adapters.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "com.redis.infra.configuration,com.redis.infra.adapters.repository," +
		"com.redis.controller")
@EnableRedisRepositories(basePackages = "com.redis.infra.adapters.repository")
public class SpringRedisApplication {

	@Autowired
	private IUserRepository userRepository;
	
	public static void main(String[] args) {

		SpringApplication.run(SpringRedisApplication.class, args);
	}

}
