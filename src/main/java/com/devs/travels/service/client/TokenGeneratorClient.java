package com.devs.travels.service.client;

import com.devs.travels.config.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = "token-generator",
        url = "https://token-generator-davi.herokuapp.com/v1",
        configuration = ClientConfiguration.class,
        fallback = TokenGeneratorFallback.class
)
public interface TokenGeneratorClient {

    @GetMapping("/token")
    String getToken(@RequestParam("email") String email);
}
