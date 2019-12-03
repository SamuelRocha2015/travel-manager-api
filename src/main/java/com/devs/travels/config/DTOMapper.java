package com.devs.travels.config;

import com.devs.travels.domain.JwtAuthentication;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.JwtAuthenticationDTO;
import com.devs.travels.domain.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper( DTOMapper.class );

    @Mapping(target = "role", constant = "ROLE_EMPLOYEE")
    User toUser(UserDTO entity);

    JwtAuthenticationDTO toJwtAuthenticationDTO(JwtAuthentication entity);

    UserDTO toUserDto(User entity);
}
