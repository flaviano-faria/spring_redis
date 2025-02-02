package com.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.redis.entity.User;

public interface UserRepository extends CrudRepository<User, String>{

}
