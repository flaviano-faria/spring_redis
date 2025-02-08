package com.redis.infra.adapters.repository;

import com.redis.domain.UserDTO;
import com.redis.domain.ports.repository.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository implements UserRepositoryPort {

    private IUserRepository iUserRepository;

    public UserRepository(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return List.of();
    }

    @Override
    public void saveUser(UserDTO userDTO) {

    }
}
