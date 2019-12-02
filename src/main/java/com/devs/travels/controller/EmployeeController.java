package com.devs.travels.controller;

import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.exception.ForbiddenException;
import com.devs.travels.repository.UserRepository;
import com.devs.travels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final UserService service;
    private final UserRepository repository;

    @Autowired
    public EmployeeController(UserService service, UserRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/register")
    public User createAppointment(@Valid @RequestBody UserDTO userDTO) {
        boolean existsByEmail = repository.existsByEmail(userDTO.getEmail());
        boolean existsByCpf = repository.existsByCpf(userDTO.getCpf());

        if(existsByEmail)
            throw new ForbiddenException("A User with email: " + userDTO.getEmail() + " already exists");
        else if(existsByCpf)
            throw new ForbiddenException("A User with cpf: " + userDTO.getCpf() + " already exists");

        return service.createEmplyee(userDTO);
    }
}
