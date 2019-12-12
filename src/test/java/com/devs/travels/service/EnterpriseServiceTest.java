package com.devs.travels.service;

import static com.devs.travels.util.Constants.DEFAULT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devs.travels.databuilder.builder.EnterpriseBuilder;
import com.devs.travels.domain.Enterprise;
import com.devs.travels.exception.ConflictException;
import com.devs.travels.repository.EnterpriseRepository;

@ExtendWith(MockitoExtension.class)
public class EnterpriseServiceTest {
	
	private Enterprise enterprise;

	@InjectMocks
	private EnterpriseService service;
	@Mock
	private EnterpriseRepository repository;

	@BeforeEach
	void setUp() {
		enterprise = new EnterpriseBuilder().build();
	}

	@Test
	void shouldCreateEnterpriseWhenValid() {
		configCreateEnterpriseWhenValid();
		Enterprise created = service.create(enterprise);
		assertCreateEnterpriseWhenValid(created);
	}

	private void configCreateEnterpriseWhenValid() {
		configExistsByCNPJ(Boolean.FALSE);

		Enterprise enterpriseCreated = new EnterpriseBuilder().buildMock();
		when(repository.save(enterprise)).thenReturn(enterpriseCreated);
	}
	
	private void configExistsByCNPJ(boolean exists) {
		when(repository.existsByCNPJ(enterprise.getCNPJ())).thenReturn(exists);
	}
	
	private void assertCreateEnterpriseWhenValid(Enterprise created) {
		assertNotNull(created);
		assertTrue(created.isActive());
		assertEquals(DEFAULT_ID, created.getId());
	}
	
	@Test
	void shouldExceptionWhenUserInvalid() {
		configExistsByCNPJ(Boolean.TRUE);

		assertThrows(ConflictException.class, () -> service.create(enterprise),
				EnterpriseService.THIS_CNPJ_IS_ALREADY_USED);
	}
	
	@Test
	void shouldNotCreateEnterpriseWhenNull() {
		assertThrows(NullPointerException.class, () -> service.create(null));	
	}

}
