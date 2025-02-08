package com.redis.domain.ports.repository;

import com.redis.domain.UserDTO;
import java.util.List;

public interface UserRepositoryPort {

    public List<UserDTO> findAllUsers();
    public void saveUser(UserDTO userDTO);

}
