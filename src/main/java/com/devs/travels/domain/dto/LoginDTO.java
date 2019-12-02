package com.devs.travels.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
