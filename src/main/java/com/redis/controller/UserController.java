package com.redis.controller;

import com.redis.domain.ports.interfaces.UserServicePort;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(path = "/findbyid/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        return this.userServicePort.findUserById(id);
    }
	
}
