package com.redis.entity;

import java.io.Serializable;

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
}
