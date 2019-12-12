package com.devs.travels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs.travels.domain.Enterprise;
import com.devs.travels.exception.ConflictException;
import com.devs.travels.repository.EnterpriseRepository;

import lombok.NonNull;

@Service
public class EnterpriseService {

	protected static final String THIS_CNPJ_IS_ALREADY_USED = "This CNPJ is already used.";
	
	private final EnterpriseRepository repository;

	@Autowired
	public EnterpriseService(EnterpriseRepository repository) {
		this.repository = repository;
	}
	
	public Enterprise create(@NonNull Enterprise enterprise) {
		 if (isEnterpriseRegistered(enterprise))
	            throw new ConflictException(THIS_CNPJ_IS_ALREADY_USED);
		 
		return createEnterprise(enterprise);
	}

	private boolean isEnterpriseRegistered(Enterprise enterprise) {
		 return repository.existsByCNPJ(enterprise.getCNPJ());
	}
	
	private Enterprise createEnterprise(Enterprise enterprise) {
		return repository.save(enterprise);
	}
}
