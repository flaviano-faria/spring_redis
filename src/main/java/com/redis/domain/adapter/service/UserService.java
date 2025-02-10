package com.redis.domain.adapter.service;

import com.redis.domain.User;
import com.redis.domain.UserDTO;
import com.redis.domain.ports.repository.UserRepositoryPort;

import com.redis.domain.ports.interfaces.UserServicePort;

public class UserService implements UserServicePort{
	

	private final UserRepositoryPort userRepositoryPort;

	public UserService(UserRepositoryPort userRepositoryPort){
		this.userRepositoryPort = userRepositoryPort;
	}

	@Override
	public void createUser(UserDTO userDTO) {
		User user = new User(userDTO.getId(), userDTO.getName());
		userRepositoryPort.saveUser(user);
	}


}
