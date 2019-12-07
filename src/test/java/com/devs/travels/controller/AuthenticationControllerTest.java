package com.devs.travels.controller;

import com.devs.travels.config.security.JwtTokenProvider;
import com.devs.travels.databuilder.builder.LoginBuilder;
import com.devs.travels.domain.dto.JwtAuthenticationDTO;
import com.devs.travels.domain.dto.LoginDTO;
import com.devs.travels.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest extends AbstractTest {

    private static final String BASE_URL = "/v1/authentication";
    private static final String LOGIN = "/login";
    private static final String DEFAULT_JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    @InjectMocks
    private AuthenticationController controller;
    @Mock
    private JwtTokenProvider tokenProvider;
    @Mock
    private AuthenticationService service;

    private LoginDTO loginDTO;

    @BeforeEach
    void setUp() {
        mvc = getMockMvc(controller);
        loginDTO = new LoginBuilder().buildDTO();
    }


    @Test
    void shouldAuthenticateUserWhenLoginValid() throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken();
        when(service.getAuthentication(any())).thenReturn(authenticationToken);

        when(tokenProvider.generateJwt(any())).thenReturn(DEFAULT_JWT);

        String uri = BASE_URL + LOGIN;

        mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(loginDTO)))
                .andExpect(status().is(HTTP_STATUS_OK));


    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
    }


}
