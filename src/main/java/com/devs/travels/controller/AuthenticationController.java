package com.devs.travels.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.config.security.JwtTokenProvider;
import com.devs.travels.domain.JwtAuthentication;
import com.devs.travels.domain.dto.JwtAuthenticationDTO;
import com.devs.travels.domain.dto.LoginDTO;
import com.devs.travels.service.AuthenticationService;

@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationController {

	private final JwtTokenProvider tokenProvider;
	private final AuthenticationService service;
	private final DTOMapper mapper;

	@Autowired
	public AuthenticationController(JwtTokenProvider tokenProvider, DTOMapper mapper, 
			AuthenticationService service) {
		this.tokenProvider = tokenProvider;
		this.service = service;
		this.mapper = mapper;
	}

	@PostMapping("/login")
	public JwtAuthenticationDTO authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
		Authentication authentication = service.getAuthentication(mapper.toLogin(loginDTO));
		String jwt = tokenProvider.generateJwt(authentication);
		return mapper.toJwtAuthenticationDTO(new JwtAuthentication(jwt));
	}
}