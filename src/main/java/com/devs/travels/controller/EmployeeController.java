package com.devs.travels.controller;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User createAppointment(@Valid @RequestBody UserDTO userDTO) {
        return service.createEmplyee(mapper.toEmployeeUserDTO(userDTO));
    }
}
