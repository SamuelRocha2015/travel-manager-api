package com.devs.travels.databuilder.builder.dto;

import com.devs.travels.domain.dto.client.TokenInfoDTO;

public class TokenInfoDTOBuilder {

    private TokenInfoDTO tokenInfoDTO;

    public TokenInfoDTOBuilder() {
        tokenInfoDTO = new TokenInfoDTO();
        tokenInfoDTO.setId("XPTO");
        tokenInfoDTO.setValue("XPTOY");
        tokenInfoDTO.setMessage("Your token is successfully validated.");
        tokenInfoDTO.setValid(true);
    }

    public TokenInfoDTO build(){
        return tokenInfoDTO;
    }

}
