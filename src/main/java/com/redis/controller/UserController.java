package com.redis.controller;

import com.redis.domain.ports.interfaces.UserServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.redis.domain.UserDTO;

import java.util.List;

@RestController
public class UserController {

	private final UserServicePort userServicePort;

    public UserController(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @PostMapping
	public void createUser(@RequestBody UserDTO userDTO) {

		this.userServicePort.createUser(userDTO);
	}

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return this.userServicePort.getAllUsers();
    }
	
}
