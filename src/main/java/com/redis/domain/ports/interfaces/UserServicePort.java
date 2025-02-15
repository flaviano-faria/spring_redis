package com.redis.domain.ports.interfaces;

import com.redis.domain.User;
import com.redis.domain.UserDTO;

import java.util.List;

public interface UserServicePort {
	
	public void createUser(UserDTO userDTO);
	public List<UserDTO> getAllUsers();
	public UserDTO findUserById(String id);
}
