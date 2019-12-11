package com.devs.travels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devs.travels.domain.Enterprise;

@Repository
public interface EnterpriseRepository  extends JpaRepository<Enterprise, Long> {
	boolean existsByCNPJ(String CNPJ);
}
