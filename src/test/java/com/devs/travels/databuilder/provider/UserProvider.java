package com.devs.travels.databuilder.provider;

import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.dto.UserDTO;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class UserProvider extends BasicAuthProvider {

	private static final String CPF_WITH_12_CHARS = "000000000000";
	private static final String CPF_WITH_10_CHARS = "0000000000";
	private static final String NAME_IS_NULL = "Name is null.";
	private static final String CPF_INVALID_WITH_12_CHARS = "CPF invalid with 12 chars.";
	private static final String CPF_INVALID_WITH_10_CHARS = "CPF invalid with 10 chars.";
	private static final String CPF_IS_NULL = "CPF is null.";

	public static Stream<Arguments> provider() {
		UserDTO dto1 = new UserBuilder().buildDTO();
		dto1.setEmail(null);
		
		UserDTO dto2 = new UserBuilder().buildDTO();
		dto2.setEmail(INVALID_EMAIL);

		UserDTO dto3 = new UserBuilder().buildDTO();
		dto3.setName(null);
		
		UserDTO dto4 = new UserBuilder().buildDTO();
		dto4.setPassword(INVALID_PASSWORD_VERY_LONG);

		UserDTO dto5 = new UserBuilder().buildDTO();
		dto5.setPassword(null);

		UserDTO dto6 = new UserBuilder().buildDTO();
		dto6.setCpf(CPF_WITH_12_CHARS);
		
		UserDTO dto7 = new UserBuilder().buildDTO();
		dto7.setCpf(CPF_WITH_10_CHARS);

		UserDTO dto8 = new UserBuilder().buildDTO();
		dto8.setCpf(null);

		return Stream.of(
				Arguments.of(dto1, EMAIL_IS_NULL ),
				Arguments.of(dto2, EMAIL_IS_INVALID ),
				Arguments.of(dto3, NAME_IS_NULL),
				Arguments.of(dto4, PASSWORD_IS_VERY_LONG ),
				Arguments.of(dto5, PASSWORD_IS_NULL),
				Arguments.of(dto6, CPF_INVALID_WITH_12_CHARS),
				Arguments.of(dto7, CPF_INVALID_WITH_10_CHARS),
				Arguments.of(dto8, CPF_IS_NULL)
			);
	}
}

