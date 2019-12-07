package com.devs.travels.controller;

import com.devs.travels.databuilder.builder.TokenInformationDTOBuilder;
import com.devs.travels.domain.dto.client.TokenInformationDTO;
import com.devs.travels.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractTest  {
    private static final String BASE_URL = "/v1/user";
    public static final String ACTIVATE_USER = "/activate-user";
    public static final String TOKEN_PARAMETER = "?token=XPTOY";

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    private TokenInformationDTO tokenInformationDTOtoCreate;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        tokenInformationDTOtoCreate = new TokenInformationDTOBuilder().build();
    }

    @Test
    void shouldStatus200WhenActivateUser() throws Exception {
    	when(service.activeUser(any())).thenReturn(tokenInformationDTOtoCreate);
    	String uri = BASE_URL + ACTIVATE_USER + TOKEN_PARAMETER;

    	mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(tokenInformationDTOtoCreate)))
                .andExpect(status().is(HTTP_STATUS_OK));
    }
}
