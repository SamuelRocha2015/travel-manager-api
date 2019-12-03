package com.devs.travels.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.exception.ConflictException;
import com.devs.travels.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

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


    @ParameterizedTest
    @MethodSource("com.devs.travels.databuilder.provider.UserProvider#provideUserDTO")
    void shouldStatus400WhenCreateUserNotValid(UserDTO dto) throws Exception{
        String uri = BASE_URL + REGISTER;

        mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_BAD_REQUEST));
    }

    @Test
    void shouldStatus409WhenCreateWithSameInformations() throws Exception {
    	when(service.createEmployee(any())).thenThrow(ConflictException.class);
    	String uri = BASE_URL + REGISTER;
    	
    	mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(userToCreate )))
                .andExpect(status().is(HTTP_CONFLICT));
    }
}
