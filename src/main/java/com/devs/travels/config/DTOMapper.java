package com.devs.travels.config;

import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper( DTOMapper.class );

    @Mapping(target = "role", constant = "EMPLOYEE")
    UserDTO toEmployeeUserDTO(User entity);

    User toUserDto(UserDTO userDTO);
}
