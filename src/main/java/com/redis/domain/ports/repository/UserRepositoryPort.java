package com.redis.domain.ports.repository;

import com.redis.domain.User;
import java.util.List;

public interface UserRepositoryPort {

    public List<User> findAllUsers();
    public void saveUser(User user);
    public User findById(String id);
    public void deleteUserById(String id);
}
