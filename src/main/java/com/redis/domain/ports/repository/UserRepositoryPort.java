package com.redis.domain.ports.repository;

import com.redis.domain.User;
import com.redis.domain.UserDTO;
import java.util.List;

public interface UserRepositoryPort {

    public List<User> findAllUsers();
    public void saveUser(User user);

}
