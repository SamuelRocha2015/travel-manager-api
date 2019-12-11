package com.devs.travels.domain.dto;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnterpriseDTO {
	
	private Long id;
    @NotBlank
    private String name;
    private String fantasyName;
    private String area;
    @NotBlank
    @CNPJ
    private String CNPJ;
    private String idManager;
    
}
