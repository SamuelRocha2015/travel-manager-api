package com.devs.travels.service;

import com.devs.travels.domain.dto.client.TokenInformationDTO;
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
		user.setPassword(encoder.encode(user.getPassword()));
        tokenGenerator.getToken(user.getEmail());
		return repository.save(user);
	}

    public TokenInformationDTO activeUser(String token) {
        return tokenGenerator.getInformation(token);
    }
}
