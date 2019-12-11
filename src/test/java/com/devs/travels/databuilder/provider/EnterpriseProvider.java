package com.devs.travels.databuilder.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import com.devs.travels.databuilder.builder.EnterpriseBuilder;
import com.devs.travels.domain.dto.EnterpriseDTO;

public class EnterpriseProvider {

	private static final String CNPJ_IS_INVALID_WITH_15_CHARS = "CNPJ is invalid with 15 chars.";
	private static final String CNPJ_IS_INVALID_WITH_3_CHARS = "CNPJ is invalid with 3 chars.";
	private static final String CNPJ_IS_NULL = "CNPJ is null.";
	private static final String CNPJ_IS_EMPTY = "CNPJ is empty.";
	private static final String NAME_IS_NULL = "Name is null.";
	private static final String NAME_IS_EMPTY = "Name is empty.";

	public static Stream<Arguments> provider() {
		
		EnterpriseDTO dto1 = new EnterpriseBuilder().buildDTO();
		dto1.setName("");
		
		EnterpriseDTO dto2 = new EnterpriseBuilder().buildDTO();
		dto2.setName(null);
		
		EnterpriseDTO dto3 = new EnterpriseBuilder().buildDTO();
		dto3.setCNPJ("");
		
		EnterpriseDTO dto4 = new EnterpriseBuilder().buildDTO();
		dto4.setCNPJ(null);
		
		EnterpriseDTO dto5 = new EnterpriseBuilder().buildDTO();
		dto5.setCNPJ("000");
		
		EnterpriseDTO dto6 = new EnterpriseBuilder().buildDTO();
		dto6.setCNPJ("000000000000000");
		
		return Stream.of(
				Arguments.of(dto1, NAME_IS_EMPTY),
				Arguments.of(dto2, NAME_IS_NULL),
				Arguments.of(dto3, CNPJ_IS_EMPTY),
				Arguments.of(dto4, CNPJ_IS_NULL),
				Arguments.of(dto5, CNPJ_IS_INVALID_WITH_3_CHARS),
				Arguments.of(dto6, CNPJ_IS_INVALID_WITH_15_CHARS)
		);
	}
}
