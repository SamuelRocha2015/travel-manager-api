package com.devs.travels.service;

import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.RoleEnum;
import com.devs.travels.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.devs.travels.domain.User;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    private Long id = 123L;
    private String email = "diogomasalmada@digen.com.br";
    private User user;

    @InjectMocks
    private CustomUserDetailsService service;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        user = new UserBuilder().build();
        user.setRole(RoleEnum.ROLE_EMPLOYEE);
    }

    @Test
    void shouldExceptionWhenFindUserNoExists() {
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->   service.findUserById(id),
                CustomUserDetailsService.USER_NOT_FOUND );
    }

    @Test
    void shouldExceptionWhenLoadUserNoExists() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->   service.loadUserByUsername(email),
                CustomUserDetailsService.USER_NOT_FOUND );
    }


    @Test
    void shouldFindUserWhenPassIdValid() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDetails userDetails  = service.findUserById(id);

        assertNotNull(userDetails);
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }


    @Test
    void shouldFindUserWhenPassEmailValid() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails  = service.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }
}
