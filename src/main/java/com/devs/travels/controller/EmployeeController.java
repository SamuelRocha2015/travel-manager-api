package com.devs.travels.controller;

import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final UserService userService;

    @Autowired
    public EmployeeController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createAppointment(@Valid @RequestBody UserDTO userDTO) throws IOException {
        return userService.createEmplyee(userDTO);
    }
}
