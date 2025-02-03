package com.redis.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redis.domain.UserDTO;

@RestController
public class UserController {

	public void createUser(@RequestBody UserDTO userDTO) {
		
	}
	
}
