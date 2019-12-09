package com.devs.travels.controller;

import com.devs.travels.databuilder.builder.dto.TokenInfoDTOBuilder;
import com.devs.travels.domain.dto.client.TokenInfoDTO;
import com.devs.travels.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractTest  {

    private static final String BASE_URL = "/v1/user";
    private static final String ACTIVATE_USER = "/activate-user";
    private static final String TOKEN_PARAMETER = "?token=XPTOY";

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    private TokenInfoDTO tokenDTOtoCreate;

    @BeforeEach
    void setUp() {
        mvc = getMockMvc(controller);
        tokenDTOtoCreate = new TokenInfoDTOBuilder().build();
    }

    @Test
    void shouldStatus200WhenActivateUser() throws Exception {
    	when(service.activeUser(any())).thenReturn(tokenDTOtoCreate);
    	String uri = BASE_URL + ACTIVATE_USER + TOKEN_PARAMETER;

    	mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(tokenDTOtoCreate)))
                .andExpect(status().is(HTTP_STATUS_OK));
    }
}
