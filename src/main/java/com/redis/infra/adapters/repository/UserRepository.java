package com.redis.infra.adapters.repository;

import com.redis.domain.User;
import com.redis.domain.ports.repository.UserRepositoryPort;
import com.redis.infra.adapters.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepository implements UserRepositoryPort {

    private final RestClient.Builder builder;
    private IUserRepository iUserRepository;

    public UserRepository(IUserRepository iUserRepository, RestClient.Builder builder) {
        this.iUserRepository = iUserRepository;
        this.builder = builder;
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> listUserEntity = (List<UserEntity>) this.iUserRepository.findAll();

        return listUserEntity.stream().map(UserEntity::toUser).collect(Collectors.toList());
    }

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = new UserEntity(user.getId(), user.getName());
        this.iUserRepository.save(userEntity);
    }

    @Override
    public User findById(String id) {
        UserEntity userEntity = this.iUserRepository.findById(id).orElse(new UserEntity("",""));

        return (User) userEntity.toUser();
    }

    @Override
    public void deleteUserById(String id) {
        this.iUserRepository.deleteById(id);
    }
}
