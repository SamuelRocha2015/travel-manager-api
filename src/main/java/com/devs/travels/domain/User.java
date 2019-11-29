package com.devs.travels.domain;

import com.easyClinic.auditoria.UserDateAudit;
import com.easyClinic.payload.usuario.UsuarioRequest;
import com.easyClinic.util.Utils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = {"login", "cpf"}) })
public class User extends UserDateAudit{

	/**
	 *
	 */
	private static final long serialVersionUID = 4467793327388448063L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotNull
	@Size(min = 11, max = 11)
	@Column(name = "cpf", nullable = false)
	private String cpf;

	@NotNull
	@Size(min = 6, max = 8)
	@Column(name = "login", nullable = false)
	private String login;

	@NotNull
	@Size(min = 6)
	@Column(name = "senha", nullable = false)
	private String senha;

	@Email
	@NotNull
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "ativo")
	private Boolean ativo;

	public User() {

	}

	public User(UsuarioRequest usuarioRequest) {
		this.cpf = usuarioRequest.getCpf();
		this.nome = usuarioRequest.getNome();
		this.login = usuarioRequest.getLogin();
		this.senha = usuarioRequest.getSenha();
		this.email = usuarioRequest.getEmail();
		this.ativo = Utils.setBool(usuarioRequest.getAtivo());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
