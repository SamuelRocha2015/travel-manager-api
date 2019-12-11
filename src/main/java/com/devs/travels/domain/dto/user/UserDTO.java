package com.devs.travels.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 50)
    private String password;

    @NotNull
    @Size(max = 11, min = 11)
    private String cpf;
}
