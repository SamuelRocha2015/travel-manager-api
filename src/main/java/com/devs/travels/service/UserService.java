package com.devs.travels.service;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.exception.ForbiddenException;
import com.devs.travels.repository.UserRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createEmplyee(User user) {
        boolean valid = userRepository.existsByEmailOrCpf(user.getEmail(), user.getCpf());

        if (valid)
            throw new ForbiddenException("This email or cpf is already used.");

        activeUSer(user);
        return createUser(user);
    }

    private User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

    //TODO create service who consumes token-generator-api (https://token-generator-davi.herokuapp.com/swagger-ui.html),
    //TODO and verify a valid token by email to activate a user
    private void activeUSer(User user) {
        user.setActive(true);
    }
}
