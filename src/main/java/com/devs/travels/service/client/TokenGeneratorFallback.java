package com.devs.travels.service.client;

import org.springframework.stereotype.Component;

@Component
public class TokenGeneratorFallback implements  TokenGeneratorClient {

    @Override
    public String getToken(String email) {
        return "NO TOKEN";
    }

}
