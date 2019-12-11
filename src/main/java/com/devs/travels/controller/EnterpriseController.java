package com.devs.travels.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.domain.Enterprise;
import com.devs.travels.domain.User;
import com.devs.travels.domain.dto.EnterpriseDTO;
import com.devs.travels.domain.dto.UserDTO;
import com.devs.travels.service.EnterpriseService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/enterprise")
public class EnterpriseController implements MVCController {
	   
	private final EnterpriseService service;
	private final DTOMapper mapper;
	
    @Autowired
    public EnterpriseController(EnterpriseService service, DTOMapper mapper) {
		this.service = service;
		this.mapper = mapper;
    }
    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("ADMIN")
    public EnterpriseDTO create(@Valid @RequestBody EnterpriseDTO DTO) {
    	Enterprise enterprise = service.create(mapper.toEnterprise(DTO));
        return mapper.toEnterpriseDTO(enterprise);
    }
}
