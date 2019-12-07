package com.devs.travels.service;

import com.devs.travels.databuilder.builder.LoginBuilder;
import com.devs.travels.domain.Login;
import com.devs.travels.exception.NotFoundException;
import com.devs.travels.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
		when(repository.existsByEmailAndIsActiveTrue(login.getEmail())).thenReturn(Boolean.FALSE);

		assertThrows(NotFoundException.class, () -> service.getAuthentication(login),
    			 AuthenticationService.USER_NOT_FOUND );
	}

	@Test
	void shouldGetAuthenticationWhenLoginExists() {
		configGetAuthenticationWhenLoginExists();

		Authentication authentication = service.getAuthentication(login);

		assertsGetAuthenticationWhenLoginExists(authentication);
	}

	private void configGetAuthenticationWhenLoginExists() {
		when(repository.existsByEmailAndIsActiveTrue(login.getEmail())).thenReturn(Boolean.TRUE);

		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken();
		when(authentication.authenticate(any())).thenReturn(authenticationToken);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
	}

	private void assertsGetAuthenticationWhenLoginExists(Authentication authentication) {
		assertNotNull(authentication);
		assertEquals(login.getEmail(), authentication.getPrincipal());
		assertEquals(login.getPassword(), authentication.getCredentials());
	}

}
