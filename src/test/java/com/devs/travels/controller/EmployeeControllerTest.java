package com.devs.travels.controller;

import com.devs.travels.databuilder.UserBuilder;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerTest extends AbstractTest  {
    private static final String BASE_URL = "/v1/employee";
    private static final String PRODUCT_ID = "/321";
    public static final String REGISTER = "/register";

    @Mock
    private UserService service;

    @InjectMocks
    private EmployeeController controller;

    private UserDTO userToCreate;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        userToCreate = new UserBuilder().userDTOMock();
    }

    @Test
    void shouldStatus201WhenCreateUserValid() throws Exception{
        String uri = BASE_URL + REGISTER;

        mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(userToCreate)))
                .andExpect(status().is(HTTP_STATUS_CREATED));
    }


    @Test
    void shouldStatus400WhenCreateUserNotValid() throws Exception{
        String uri = BASE_URL + REGISTER;
        userToCreate.setEmail(null); //TODO - Create other tests scenarios with parameterized

        mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(userToCreate)))
                .andExpect(status().is(HTTP_BAD_REQUEST));
    }


}
