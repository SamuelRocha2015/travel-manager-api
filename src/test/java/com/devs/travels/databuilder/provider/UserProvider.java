package com.devs.travels.databuilder.provider;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.dto.UserDTO;

public class UserProvider {

	private static final String INVALID_EMAIL = "email";
	private static final String INVALID_PASSWORD_VERY_LONG = "9TT9G0AB7FQRANCQK6AJI6S7EFSDTR0A6CSXFPCHYKONI6S89UXNS";
	private static final String CPF_WITH_12_CHARS = "000000000000";
	private static final String CPF_WITH_10_CHARS = "0000000000";

	public static Stream<Arguments> provideUserDTO() {
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
				Arguments.of(userToCreate1, "Email is null." ),
				Arguments.of(userToCreate2, "Email is invalid." ),
				Arguments.of(userToCreate3, "Name is null." ),
				Arguments.of(userToCreate4, "Password is very long." ),
				Arguments.of(userToCreate5, "Password is null." ),
				Arguments.of(userToCreate6, "CPF invalid with 12 chars." ),
				Arguments.of(userToCreate7, "CPF invalid with 10 chars." ),
				Arguments.of(userToCreate8, "CPF is null." )
			);
	}
}

