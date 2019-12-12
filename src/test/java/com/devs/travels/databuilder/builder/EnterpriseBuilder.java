package com.devs.travels.databuilder.builder;

import com.devs.travels.config.DTOMapper;
import com.devs.travels.domain.Enterprise;
import com.devs.travels.domain.dto.EnterpriseDTO;
import static com.devs.travels.util.Constants.DEFAULT_ID;

public class EnterpriseBuilder {

	private Enterprise enterprise;
	
	public EnterpriseBuilder() {
		enterprise = new Enterprise();
		enterprise.setCNPJ("95874540000100");
		enterprise.setName("Daiane e Severino Pizzaria Delivery ME");
		enterprise.setFantasyName("Pizzaria Delivery ME");
		enterprise.setArea("Delivery");
	}
	
	public Enterprise build(){
		return enterprise;
    }
	
	public Enterprise buildMock(){
		enterprise.setId(DEFAULT_ID);
		return enterprise;
	}

	public EnterpriseDTO buildDTO(){
		return DTOMapper.INSTANCE.toEnterpriseDTO(enterprise);
	}
}
