package com.redis.app;

import java.util.ArrayList;
import java.util.List;

import com.redis.entity.UserEntity;
import com.redis.infra.adapters.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "com.redis.configuration,com.redis.repository")
@EnableRedisRepositories(basePackages = "com.redis.repository")
public class SpringRedisApplication implements CommandLineRunner{

	@Autowired
	private IUserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRedisApplication.class, args);
	}
	
	@Override
	  public void run(String... args) throws Exception {
		
		UserEntity user = new UserEntity("345", "user02");
		userRepository.save(user);

		List<UserEntity> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		
		System.out.println(userList.size());
		
		
	}

}
