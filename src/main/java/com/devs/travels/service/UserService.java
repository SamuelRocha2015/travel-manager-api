package com.devs.travels.service;

import com.devs.travels.config.security.JwtTokenProvider;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.repository.UserRepository;
import com.easyClinic.model.Usuario;
import com.easyClinic.payload.usuario.UsuarioRequest;
import com.easyClinic.repository.UsuarioRepository;
import com.easyClinic.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserDTO userDTO) {
        User user = new User(userRequest);

        usuario.setSenha(passwordEncoder.encode(userRequest.getPassword()));

        usuarioRepository.save(usuario);
    }
}
