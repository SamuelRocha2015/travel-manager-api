package com.devs.travels.domain.dto.user;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtAuthenticationDTO {

    private String accessToken;
    private String tokenType;

}
