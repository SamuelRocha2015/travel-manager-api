package com.devs.travels.asserts;

import org.assertj.core.api.AbstractAssert;
import org.springframework.security.core.userdetails.UserDetails;
import com.devs.travels.domain.User;

public class UserDetailsAssert extends AbstractAssert<UserDetailsAssert, UserDetails> {

	public UserDetailsAssert(UserDetails actual) {
		super(actual, UserDetailsAssert.class);
	}

	public static UserDetailsAssert assertThat(UserDetails actual) {
	    return new UserDetailsAssert(actual);
	}
	
	public UserDetailsAssert EqualsTo(User expected) {
		isNotNull();
		
		if(!actual.getUsername().equals(expected.getEmail()))
			 failWithMessage("Expected UserDetails to have username %s", expected.getEmail());
		
		if(!actual.getPassword().equals(expected.getPassword()))
			 failWithMessage("Expected UserDetails to have password %s", expected.getPassword());
		
		return this;
	}
	
}
