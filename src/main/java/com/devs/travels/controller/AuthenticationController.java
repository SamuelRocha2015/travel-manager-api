package com.devs.travels.controller;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.config.security.JwtTokenProvider;
import com.devs.travels.domain.JwtAuthentication;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.JwtAuthenticationDTO;
import com.devs.travels.domain.dto.LoginDTO;
import com.devs.travels.exception.BadRequestException;
import com.devs.travels.exception.NotFoundException;
import com.devs.travels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;
    private final DTOMapper mapper;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
                                    JwtTokenProvider tokenProvider, DTOMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    public JwtAuthenticationDTO authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("User Not Found."));

        if (user.isActive()) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            return mapper.toJwtAuthenticationDTO(new JwtAuthentication(jwt));
        } else {
            throw new BadRequestException("Inactive user or incorrect credentials");
        }
    }

}