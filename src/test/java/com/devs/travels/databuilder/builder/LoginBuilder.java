package com.devs.travels.databuilder.builder;

import com.devs.travels.domain.Login;

public class LoginBuilder {

	private Login login;
	
	public LoginBuilder() {
		login = new Login();
		login.setEmail("fabianasandramelo_@l3ambiental.com.br");
		login.setPassword("ggqdBeYFSC");
	}
	
	public Login build(){
		return login;
    }

}