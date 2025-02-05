package com.redis.domain.ports.interfaces;

import com.redis.entity.User;

public interface UserServicePort {
	
	public void createUser(User user);

}
