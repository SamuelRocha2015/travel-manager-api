package com.devs.travels.databuilder.provider;

import com.devs.travels.databuilder.builder.LoginBuilder;
import com.devs.travels.domain.dto.user.LoginDTO;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class LoginProvider  extends BasicAuthProvider  {

    public static Stream<Arguments> provider() {
        LoginDTO dto1 = new LoginBuilder().buildDTO();
        dto1.setEmail(null);


        LoginDTO dto2 = new LoginBuilder().buildDTO();
        dto2.setEmail(INVALID_EMAIL);

        LoginDTO dto3 = new LoginBuilder().buildDTO();
        dto3.setPassword(null);

        LoginDTO dto4 = new LoginBuilder().buildDTO();
        dto4.setPassword(INVALID_PASSWORD_VERY_LONG);

        return Stream.of(
                Arguments.of(dto1, EMAIL_IS_NULL),
                Arguments.of(dto2, EMAIL_IS_INVALID),
                Arguments.of(dto3, PASSWORD_IS_NULL),
                Arguments.of(dto4, PASSWORD_IS_VERY_LONG)
        );
    }
}
