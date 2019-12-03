package com.devs.travels.service;


import com.devs.travels.domain.User;
import com.devs.travels.domain.UserPrincipal;
import com.devs.travels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) {

        User user = userRepository.findByEmail(usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with email : " + usernameOrEmail + " not found!"));

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new
                UsernameNotFoundException("User with id : " + id + " not found."));

        return UserPrincipal.create(user);
    }
}
////