package com.devs.travels.domain.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserEnterpriseDTO extends  UserDTO {

    @NotNull
    private String role;
}
