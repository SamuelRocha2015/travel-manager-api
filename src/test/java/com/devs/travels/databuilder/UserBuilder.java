package com.devs.travels.databuilder;

import com.devs.travels.domain.User;

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
}
