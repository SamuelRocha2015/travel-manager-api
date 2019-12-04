package com.devs.travels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devs.travels.domain.User;
import com.devs.travels.exception.ConflictException;
import com.devs.travels.repository.UserRepository;
import com.devs.travels.service.client.TokenGeneratorClient;

@Service
public class UserService {

    protected static final String THIS_EMAIL_OR_CPF_IS_ALREADY_USED = "This email or cpf is already used.";
    
	private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final TokenGeneratorClient tokenGenerator;
    
    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, TokenGeneratorClient tokenGenerator) {
        this.repository = repository;
        this.encoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public User createEmployee(User user) {
        if (isUserRegistered(user))
            throw new ConflictException(THIS_EMAIL_OR_CPF_IS_ALREADY_USED);

        return createUser(user);
    }

    private boolean isUserRegistered(User user) {
        return repository.existsByEmailOrCpf(user.getEmail(), user.getCpf());
    }

    private User createUser(User user) {
    	activeUser(user);
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
	}

    //TODO create service who consumes token-generator-api (https://token-generator-davi.herokuapp.com/swagger-ui.html),
    //TODO and verify a valid token by email to activate a user
    private void activeUser(User user) {
        user.setActive(true);
        tokenGenerator.getInformations(user.getEmail());
    }
}
