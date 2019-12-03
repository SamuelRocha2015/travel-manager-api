package com.devs.travels.controller;

import com.devs.travels.config.DTOMapper;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
@ExtendWith(SpringExtension.class)
public class AbstractTest {

    static final int HTTP_STATUS_NO_CONTENT = 204;
    static final int HTTP_STATUS_CREATED = 201;
    static final int HTTP_STATUS_OK = 200;
    static final int HTTP_BAD_REQUEST = 400;
    static final int HTTP_CONFLICT = 409;

    MockMvc mvc;

    @Mock
    DTOMapper mapper;

    String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
