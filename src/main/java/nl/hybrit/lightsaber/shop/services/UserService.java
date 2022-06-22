package nl.hybrit.lightsaber.shop.services;

import nl.hybrit.lightsaber.shop.repository.model.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    List<UserEntity> findAll();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
