package com.devs.travels.databuilder.builder;

import com.devs.travels.domain.dto.client.TokenInformationDTO;

public class TokenInformationDTOBuilder {

    private TokenInformationDTO tokenInformationDTO;

    public TokenInformationDTOBuilder() {
        tokenInformationDTO = new TokenInformationDTO();
        tokenInformationDTO.setId("XPTO");
        tokenInformationDTO.setValue("XPTOY");
        tokenInformationDTO.setMessage("Your token is successfully validated.");
        tokenInformationDTO.setValid(true);
    }

    public TokenInformationDTO build(){
        return tokenInformationDTO;
    }

}
