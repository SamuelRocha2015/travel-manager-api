package com.devs.travels.domain.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    private Long createdBy;

    @LastModifiedBy
    @Column(name = "UPDATED_BY", nullable = false)
    private Long updatedBy;

}
