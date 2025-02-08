package com.redis.domain.ports.interfaces;

import com.redis.domain.UserDTO;

public interface UserServicePort {
	
	public void createUser(UserDTO userDTO);

}
