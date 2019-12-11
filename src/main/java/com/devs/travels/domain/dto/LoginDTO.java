package com.devs.travels.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 50)
    private String password;
}
