package com.devs.travels.controller;

import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.exception.ConflictException;
import com.devs.travels.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerTest extends AbstractTest  {

    private static final String BASE_URL = "/v1/employee";

    @Mock
    private UserService service;

    @InjectMocks
    private EmployeeController controller;

    private UserDTO dto;

    @BeforeEach
    void setUp() {
        mvc = getMockMvc(controller);
        dto = new UserBuilder().buildDTO();
    }

    @Test
    void shouldStatus201WhenCreateUserValid() throws Exception{

        mvc.perform(MockMvcRequestBuilders
                .post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_CREATED));
    }


    @ParameterizedTest(name =  "{index} - {1}")
    @MethodSource("com.devs.travels.databuilder.provider.UserProvider#provider")
    void shouldStatus400WhenCreateUserNotValid(UserDTO dto, String titleTest) throws Exception{

        mvc.perform(MockMvcRequestBuilders
                .post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_BAD_REQUEST));
    }

    @Test
    void shouldStatus409WhenCreateWithSameInformation() throws Exception {
    	when(service.createEmployee(any())).thenThrow(ConflictException.class);
    	
    	mvc.perform(MockMvcRequestBuilders
                .post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(dto )))
                .andExpect(status().is(HTTP_CONFLICT));
    }
}
