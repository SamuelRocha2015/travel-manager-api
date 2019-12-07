package com.devs.travels.service.client;

import org.springframework.stereotype.Component;

import com.devs.travels.domain.dto.client.TokenInformationDTO;

@Component
public class TokenGeneratorFallback implements  TokenGeneratorClient {

    @Override
    public String getToken(String email) {
        return "NO TOKEN";
    }

	@Override
	public TokenInformationDTO getInformation(String token) {
		return new TokenInformationDTO();
	}

}
