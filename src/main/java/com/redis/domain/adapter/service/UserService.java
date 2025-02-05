package com.redis.domain.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.domain.ports.interfaces.UserServicePort;
import com.redis.entity.User;
import com.redis.repository.UserRepository;

@Service
public class UserService implements UserServicePort{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void createUser(User user) {
		userRepository.save(user);
	}

}
