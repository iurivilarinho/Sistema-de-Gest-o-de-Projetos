package com.br.project.form;

import java.util.HashSet;
import java.util.Set;

import com.br.project.models.acesso.Perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioForm {

	@NotNull(message = "O campo login não pode ser nulo!")
	@NotBlank(message = "O campo login não pode ser vazio!")
	private String login;

	@NotNull(message = "O campo login não pode ser nulo!")
	@NotBlank(message = "O campo login não pode ser vazio!")
	private String nome;

	@NotNull(message = "O campo login não pode ser nulo!")
	@NotBlank(message = "O campo login não pode ser vazio!")
	private String senha;

	@NotNull(message = "O campo login não pode ser nulo!")
	@NotBlank(message = "O campo login não pode ser vazio!")
	private String cpf;

	@NotNull(message = "O campo login não pode ser nulo!")
	@NotBlank(message = "O campo login não pode ser vazio!")
	private String celular;

	@NotNull(message = "O campo login não pode ser nulo!")
	@NotBlank(message = "O campo login não pode ser vazio!")
	private String email;

	private Set<Perfil> perfis = new HashSet<>();

	private Long idEmpresa;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

}
