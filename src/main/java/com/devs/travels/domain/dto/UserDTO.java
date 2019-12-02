package com.devs.travels.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;

    @NotBlank
    private String role;

    @Size(max = 50)
    private String password;

    @Size(max = 11, min = 11)
    private String cpf;
}
