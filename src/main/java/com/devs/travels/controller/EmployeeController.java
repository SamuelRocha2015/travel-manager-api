package com.devs.travels.controller;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final UserService service;
    private final DTOMapper mapper;

    @Autowired
    public EmployeeController(UserService service, DTOMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody UserDTO userDTO) {
        return service.createEmployee(mapper.toEmployeeUser(userDTO));
    }
}
