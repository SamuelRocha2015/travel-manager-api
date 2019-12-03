package com.devs.travels.databuilder.builder;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;

public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = new User();
        user.setEmail("fabianasandramelo_@l3ambiental.com.br");
        user.setCpf("07957908640");
        user.setName("Fabiana Sandra Melo");
        user.setPassword("ggqdBeYFSC");
    }

    public User build(){
        return user;
    }

    public User userMock(){
        user.setActive(Boolean.TRUE);
        user.setId(123L);
        return user;
    }

    public UserDTO userDTOMock(){
        return DTOMapper.INSTANCE.toUserDto(user);
    }
}
