package com.redis.infra.adapters.entity;

import java.io.Serializable;

import com.redis.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
public class UserEntity  implements Serializable {

	@Id
	private String id;
    private String name;
    
	public UserEntity(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public User toUser(){
		return new User(this.id, this.name);
	}
}
