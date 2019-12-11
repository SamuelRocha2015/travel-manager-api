package com.devs.travels.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devs.travels.repository.EnterpriseRepository;

@ExtendWith(MockitoExtension.class)
public class EnterpriseServiceTest {
	
	@InjectMocks
	private EnterpriseService service;
	@Mock
	private EnterpriseRepository repository;
}
