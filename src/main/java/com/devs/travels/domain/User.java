package com.devs.travels.domain;

import com.devs.travels.domain.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "cpf"})})
public class User extends UserDateAudit {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 20)
    private RoleEnum role;

    @Size(max = 50)
    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Size(max = 11, min = 11)
    @Column(name = "CPF", length = 11)
    private String cpf;
}
