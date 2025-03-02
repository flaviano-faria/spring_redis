package com.redis.controller;

import com.redis.domain.ports.interfaces.UserServicePort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {

    	try {
    		this.userServicePort.createUser(userDTO);
		} catch (Exception e) {
			throw e;
		}
		

        return new ResponseEntity<>("", HttpStatus.CREATED);
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
