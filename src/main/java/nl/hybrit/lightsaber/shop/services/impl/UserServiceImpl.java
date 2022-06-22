package nl.hybrit.lightsaber.shop.services.impl;

import nl.hybrit.lightsaber.shop.repository.UserRepository;
import nl.hybrit.lightsaber.shop.repository.model.UserEntity;
import nl.hybrit.lightsaber.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() ->new IllegalArgumentException("username not exist!"));
    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() ->new IllegalArgumentException("email not exist!"));
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
