package com.devs.travels.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import com.devs.travels.databuilder.builder.LoginBuilder;
import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.Login;
import com.devs.travels.domain.User;
import com.devs.travels.exception.NotFoundException;
import com.devs.travels.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
	
	private Login login;
	
	@InjectMocks
	private AuthenticationService service;
	@Mock
	private UserRepository repository;
	@Mock
	private AuthenticationManager authentication;
	
	@BeforeEach
    void setUp() {
        login = new LoginBuilder().build();
    }

	
	@Test
    void shouldGetExceptionWhenLoginNoExists() {
		when(repository.findByEmailAndActiveTrue(login.getEmail())).thenThrow(NotFoundException.class);
		
		assertThrows(NotFoundException.class, () -> service.getAuthentication(login), 
    			 AuthenticationService.USER_NOT_FOUND );
	}
}
