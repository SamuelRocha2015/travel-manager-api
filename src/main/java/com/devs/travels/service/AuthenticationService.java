package com.devs.travels.service;

import com.devs.travels.domain.Login;
import com.devs.travels.exception.NotFoundException;
import com.devs.travels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    protected static final String USER_NOT_FOUND = "User Not Found.";

    private final UserRepository userRepository;
    private final AuthenticationManager authentication;

    @Autowired
    public AuthenticationService(UserRepository userRepository, AuthenticationManager authentication) {
        this.userRepository = userRepository;
        this.authentication = authentication;
    }

    public Authentication getAuthentication(Login login) {
        if (!findUser(login))
            throw new NotFoundException(USER_NOT_FOUND);

        Authentication authentication = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        return this.authentication.authenticate(authentication);
    }

    private boolean findUser(Login login) {
        return userRepository.existsByEmailAndIsActiveTrue(login.getEmail());
    }

}
