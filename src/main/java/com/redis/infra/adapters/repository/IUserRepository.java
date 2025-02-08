package com.redis.infra.adapters.repository;

import com.redis.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import com.redis.entity.UserEntity;

public interface IUserRepository extends CrudRepository<UserEntity, String>{

}
