package com.redis.infra.adapters.repository;

import com.redis.infra.adapters.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, String>{

}
