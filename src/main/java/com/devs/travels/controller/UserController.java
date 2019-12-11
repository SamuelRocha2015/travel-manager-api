package com.devs.travels.controller;

import com.devs.travels.domain.dto.client.TokenInfoDTO;
import com.devs.travels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/user")
public class UserController implements MVCController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/activate-user")
    public TokenInfoDTO getTokenInformation(@RequestParam(name = "token") String token) {
        return userService.activeUser(token);
    }
}
