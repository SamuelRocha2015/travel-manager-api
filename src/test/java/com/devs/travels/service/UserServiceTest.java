package com.devs.travels.service;


import com.devs.travels.databuilder.UserBuilder;
import com.devs.travels.domain.User;
import com.devs.travels.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Long DEFAULT_ID = 123L;

    private User user;

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        user = new UserBuilder().build();
    }

    @Test
    void shouldCreateUserWhenUserValid() {

        when(repository.existsByEmailOrCpf(user.getEmail(), user.getCpf())).thenReturn(Boolean.FALSE);

        User userCreated = new UserBuilder().userMock();
        when(repository.save(user)).thenReturn(userCreated);

        User employee = service.createEmployee(user);

        assertNotNull(employee);
        assertTrue(employee.isActive());
        assertEquals(DEFAULT_ID, employee.getId());
        assertEquals(userCreated, user);

    }
}
