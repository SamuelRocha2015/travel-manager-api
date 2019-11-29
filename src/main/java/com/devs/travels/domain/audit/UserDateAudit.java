package com.devs.travels.domain.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"criadoPor", "atualizadoPor"},
        allowGetters = true
)
public abstract class UserDateAudit extends DateAudit {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8473611266083676233L;

	@CreatedBy
    @Column(name="criado_por", updatable = false)
    private Long criadoPor;

    @LastModifiedBy
    private Long atualizadoPor;

	public Long getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Long criadoPor) {
		this.criadoPor = criadoPor;
	}

	public Long getAtualizadoPor() {
		return atualizadoPor;
	}

	public void setAtualizadoPor(Long atualizadoPor) {
		this.atualizadoPor = atualizadoPor;
	}


}
