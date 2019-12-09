package com.devs.travels.domain.dto.client;

import lombok.Data;

@Data
public class TokenInfoDTO {
	private String id;
	private String value;
	private String message;
	private boolean valid;

}
