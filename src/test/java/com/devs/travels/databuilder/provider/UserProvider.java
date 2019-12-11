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
		UserDTO userToCreate1 = new UserBuilder().buildDTO();
		userToCreate1.setEmail(null);
		
		UserDTO userToCreate2 = new UserBuilder().buildDTO();
		userToCreate2.setEmail(INVALID_EMAIL);

		UserDTO userToCreate3 = new UserBuilder().buildDTO();
		userToCreate3.setName(null);
		
		UserDTO userToCreate4 = new UserBuilder().buildDTO();
		userToCreate4.setPassword(INVALID_PASSWORD_VERY_LONG);

		UserDTO userToCreate5 = new UserBuilder().buildDTO();
		userToCreate5.setPassword(null);

		UserDTO userToCreate6 = new UserBuilder().buildDTO();
		userToCreate6.setCpf(CPF_WITH_12_CHARS);
		
		UserDTO userToCreate7 = new UserBuilder().buildDTO();
		userToCreate7.setCpf(CPF_WITH_10_CHARS);

		UserDTO userToCreate8 = new UserBuilder().buildDTO();
		userToCreate8.setCpf(null);

		return Stream.of(
				Arguments.of(userToCreate1, EMAIL_IS_NULL ),
				Arguments.of(userToCreate2, EMAIL_IS_INVALID ),
				Arguments.of(userToCreate3, NAME_IS_NULL),
				Arguments.of(userToCreate4, PASSWORD_IS_VERY_LONG ),
				Arguments.of(userToCreate5, PASSWORD_IS_NULL),
				Arguments.of(userToCreate6, CPF_INVALID_WITH_12_CHARS),
				Arguments.of(userToCreate7, CPF_INVALID_WITH_10_CHARS),
				Arguments.of(userToCreate8, CPF_IS_NULL)
			);
	}
}

