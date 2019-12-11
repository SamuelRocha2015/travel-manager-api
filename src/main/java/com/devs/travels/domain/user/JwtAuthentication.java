package com.devs.travels.domain.user;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthentication {
    private String accessToken;
    private String tokenType;

    public JwtAuthentication(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
    }
}
