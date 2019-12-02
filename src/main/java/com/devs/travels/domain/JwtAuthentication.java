package com.devs.travels.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtAuthentication {
    private String accessToken;
    private String tokenType;

    public JwtAuthentication(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
    }
}
