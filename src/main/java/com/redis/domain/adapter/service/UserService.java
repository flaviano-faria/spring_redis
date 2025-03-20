package com.redis.domain.adapter.service;

import com.redis.domain.User;
import com.redis.domain.UserDTO;
import com.redis.domain.ports.repository.UserRepositoryPort;
import com.redis.domain.ports.interfaces.UserServicePort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServicePort{
	
	private final UserRepositoryPort userRepositoryPort;

	public UserService(UserRepositoryPort userRepositoryPort){
		this.userRepositoryPort = userRepositoryPort;
	}

	@Override
	public void createUser(UserDTO userDTO) {
		User user = User.builder()
				.id(userDTO.getId())
				.name(userDTO.getName())
				.build();
		userRepositoryPort.saveUser(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> listUser = userRepositoryPort.findAllUsers();
		return  listUser.stream().map(user -> new UserDTO(user.getId(), user.getName())).collect(Collectors.toList());
	}

	@Override
	public UserDTO findUserById(String id) {
		User user = userRepositoryPort.findById(id);
		return UserDTO.builder().id(user.getId()).name(user.getName()).build();
	}
}
