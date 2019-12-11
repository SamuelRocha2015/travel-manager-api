package com.devs.travels.controller;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.domain.dto.client.TokenInfoDTO;
import com.devs.travels.domain.dto.user.UserEnterpriseDTO;
import com.devs.travels.domain.user.User;
import com.devs.travels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/user")
public class UserController implements MVCController {

    private final UserService service;
    private final DTOMapper mapper;

    @Autowired
    public UserController(UserService service, DTOMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/activate-user")
    public TokenInfoDTO getTokenInformation(@RequestParam(name = "token") String token) {
        return service.activeUser(token);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("ADMIN")
    public User create(@Valid @RequestBody UserEnterpriseDTO userDTO) {
        return service.create(mapper.toUser(userDTO));
    }
}
