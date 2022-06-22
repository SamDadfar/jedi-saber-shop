package nl.hybrit.lightsaber.shop.services;

import nl.hybrit.lightsaber.shop.repository.model.UserEntity;

public interface UserService {

    UserEntity findByUsername(String username);
}
