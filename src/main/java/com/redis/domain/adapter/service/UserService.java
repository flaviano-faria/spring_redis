package com.redis.domain.adapter.service;

import com.redis.domain.User;
import com.redis.domain.UserDTO;
import com.redis.domain.ports.repository.UserRepositoryPort;

import com.redis.domain.ports.interfaces.UserServicePort;
import com.redis.infra.adapters.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> listUser = userRepositoryPort.findAllUsers();
		return  listUser.stream().map(User::toUserDTO).collect(Collectors.toList());
	}

}
