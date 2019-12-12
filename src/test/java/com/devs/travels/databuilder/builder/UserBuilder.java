package com.devs.travels.databuilder.builder;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;
import static com.devs.travels.util.Constants.DEFAULT_ID;

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

    public User buildMock(){
        user.setActive(Boolean.TRUE);
        user.setId(DEFAULT_ID);
        return user;
    }

    public UserDTO buildDTO(){
        return DTOMapper.INSTANCE.toUserDto(user);
    }
}
