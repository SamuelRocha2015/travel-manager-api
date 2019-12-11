package com.devs.travels.domain;

import com.devs.travels.asserts.UserDetailsAssert;
import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.user.Role;
import com.devs.travels.domain.user.User;
import com.devs.travels.domain.user.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPrincipalTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new UserBuilder().build();
        user.setRole(Role.ROLE_EMPLOYEE);
    }

    @Test
    void shouldCreateUserPrincipalWhenPassUserValid() {
        UserPrincipal principal = UserPrincipal.create(user);

        UserDetailsAssert.assertThat(principal).EqualsTo(user);

        assertEquals(user.getId(), principal.getId());
        assertEquals(user.getName(), principal.getName());
    }
}
