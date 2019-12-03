package com.devs.travels.service;

import com.devs.travels.domain.User;
import com.devs.travels.exception.ForbiddenException;
import com.devs.travels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createEmployee(User user) {
        if (userRegistered(user))
            throw new ForbiddenException("This email or cpf is already used.");

        activeUSer(user);
        return createUser(user);
    }

    private boolean userRegistered(User user) {
        return repository.existsByEmailOrCpf(user.getEmail(), user.getCpf());
    }

    private User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

    //TODO create service who consumes token-generator-api (https://token-generator-davi.herokuapp.com/swagger-ui.html),
    //TODO and verify a valid token by email to activate a user
    private void activeUSer(User user) {
        user.setActive(true);
    }
}
