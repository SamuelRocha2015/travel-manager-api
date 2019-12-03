package com.devs.travels.databuilder.provider;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.devs.travels.databuilder.builder.UserBuilder;
import com.devs.travels.domain.dto.UserDTO;

public class UserProvider {

	public static Stream<Arguments> provideUserDTO() {
		UserDTO userToCreate1 = new UserBuilder().userDTOMock();
		userToCreate1.setEmail(null);
		
		UserDTO userToCreate6 = new UserBuilder().userDTOMock();
		userToCreate6.setEmail("eamil");

		UserDTO userToCreate2 = new UserBuilder().userDTOMock();
		userToCreate2.setName(null);
		
		UserDTO userToCreate3 = new UserBuilder().userDTOMock();
		userToCreate3.setPassword("9TT9G0AB7FQRANCQK6AJI6S7ETR0A6CSXFPCHYKONI6S89UXNS");

		UserDTO userToCreate4 = new UserBuilder().userDTOMock();
		userToCreate4.setCpf("000000000000");
		
		UserDTO userToCreate5 = new UserBuilder().userDTOMock();
		userToCreate5.setCpf("0000000000");

		return Stream.of(
				Arguments.of(userToCreate1), 
				Arguments.of(userToCreate2),
				Arguments.of(userToCreate4),
				Arguments.of(userToCreate5),
				Arguments.of(userToCreate6)
			);
	}
}

