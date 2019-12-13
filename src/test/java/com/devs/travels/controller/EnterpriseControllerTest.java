package com.devs.travels.controller;

import static com.devs.travels.util.Constants.DEFAULT_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.devs.travels.databuilder.builder.EnterpriseBuilder;
import com.devs.travels.domain.dto.EnterpriseDTO;
import com.devs.travels.exception.BadRequestException;
import com.devs.travels.exception.ConflictException;
import com.devs.travels.exception.NotFoundException;
import com.devs.travels.service.EnterpriseService;

public class EnterpriseControllerTest extends AbstractTest {

	private static final String BASE_URL = "/v1/enterprise";
	
	@Mock
	private EnterpriseService service;
	@InjectMocks
	private EnterpriseController controller;

	private EnterpriseDTO dto;
	
	@BeforeEach
    void setUp() {
        mvc = getMockMvc(controller);
        dto = new EnterpriseBuilder().buildDTO();
    }
	
	// @Test
    void shouldStatus201WhenCreateEnterpriseValid() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .post(BASE_URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_CREATED));
	}
	

    // @ParameterizedTest(name =  "{index} - {1}")
    // @MethodSource("com.devs.travels.databuilder.provider.EnterpriseProvider#provider")
    void shouldStatus400WhenCreateEnterpriseNotValid(EnterpriseDTO dto, String titleTest) throws Exception {
    	mvc.perform(MockMvcRequestBuilders
                .post(BASE_URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_BAD_REQUEST));
	}
    
    // @Test
    void shouldStatus409WhenCreateWithSameInformation() throws Exception {
    	when(service.create(any())).thenThrow(ConflictException.class);
    	
    	mvc.perform(MockMvcRequestBuilders
                .post(BASE_URL)
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapToJson(dto )))
                .andExpect(status().is(HTTP_CONFLICT));
    }
    
    
    
   // @Test
    void shouldStatus200WhenUpdateUserValid() throws Exception{
    	String uri = BASE_URL + DEFAULT_ID;
    	dto.setName("Daiane e Severino Pizzaria Delivery ME Ltda");
    	dto.setId(DEFAULT_ID);
    	
        mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_OK));
    }
    
    
    //@ParameterizedTest(name =  "{index} - {1}")
    //@MethodSource("com.devs.travels.databuilder.provider.EnterpriseProvider#provider")
    void shouldStatus400WhenUpdateEnterpriseNotValid(EnterpriseDTO dto, String titleTest) throws Exception {
    	String uri = BASE_URL + DEFAULT_ID;
    	
    	mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_OK));
	}
    
    
    //@Test
    void shouldStatus400WhenUpdateResourceIdDifferent() throws Exception {
    	when(service.update(DEFAULT_ID, any())).thenThrow(BadRequestException.class);
    	String uri = BASE_URL + DEFAULT_ID;
     	
        mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_BAD_REQUEST));
    }
    
    
    // @Test
    void shouldStatus404WhenUpdateResourceNoExists() throws Exception {
    	when(service.update(DEFAULT_ID, any())).thenThrow(NotFoundException.class);
    	String uri = BASE_URL + DEFAULT_ID;
     	
        mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(APPLICATION_JSON_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().is(HTTP_NOT_FOUND));
    }
}
