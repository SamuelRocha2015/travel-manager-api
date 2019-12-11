package com.devs.travels.config;

import com.devs.travels.domain.user.JwtAuthentication;
import com.devs.travels.domain.user.Login;
import com.devs.travels.domain.user.User;
import com.devs.travels.domain.dto.user.JwtAuthenticationDTO;
import com.devs.travels.domain.dto.user.LoginDTO;
import com.devs.travels.domain.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper( DTOMapper.class );

    @Mapping(target = "role", constant = "ROLE_EMPLOYEE")
    User toEmployeeUser(UserDTO dto);

    JwtAuthenticationDTO toJwtAuthenticationDTO(JwtAuthentication entity);

    UserDTO toUserDto(User entity);

    User toUser(UserDTO dto);

    LoginDTO toLoginDTO(Login entity);
    
    Login toLogin(LoginDTO dto);

}
