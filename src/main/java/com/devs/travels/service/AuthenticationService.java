package com.devs.travels.service;
import com.devs.travels.exception.NotFoundException;
import com.devs.travels.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import com.devs.travels.domain.Login;
import com.devs.travels.domain.User;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;
	private final AuthenticationManager authentication;

	@Autowired
	public AuthenticationService(UserRepository userRepository, AuthenticationManager authentication) {
		this.userRepository = userRepository;
		this.authentication = authentication;
	}

	public Authentication getAuthentication(Login login) {
        User user = findUser(login);
        Authentication authentication = new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()); 
        return  this.authentication.authenticate(authentication );

	}

	private User findUser(Login login) {
		return userRepository.findByEmailAndActiveTrue(login.getEmail())
				.orElseThrow(() -> new NotFoundException("User Not Found."));
	}

}
