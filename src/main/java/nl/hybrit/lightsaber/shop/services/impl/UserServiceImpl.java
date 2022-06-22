package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.repository.UserRepository;
import nl.hybrit.lightsaber.shop.repository.model.UserEntity;
import nl.hybrit.lightsaber.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
    }
}
