package com.redis.domain.adapter.service;

import com.redis.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.domain.ports.interfaces.UserServicePort;
import com.redis.infra.adapters.repository.IUserRepository;

@Service
public class UserService implements UserServicePort{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public void createUser(UserDTO userDTO) {
		userRepository.save(user);
	}

}
