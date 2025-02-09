package com.redis.domain.adapter.service;

import com.redis.domain.User;
import com.redis.domain.UserDTO;
import com.redis.domain.ports.repository.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.domain.ports.interfaces.UserServicePort;
import com.redis.infra.adapters.repository.IUserRepository;

@Service
public class UserService implements UserServicePort{
	
	@Autowired
	private UserRepositoryPort userRepositoryPort;

	@Override
	public void createUser(UserDTO userDTO) {
		User user = new User(userDTO.getId(), userDTO.getName());
		userRepositoryPort.saveUser(user);
	}


}
