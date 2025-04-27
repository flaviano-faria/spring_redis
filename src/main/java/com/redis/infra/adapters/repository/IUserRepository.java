package com.redis.infra.adapters.repository;

import com.redis.infra.adapters.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, String> {

}
