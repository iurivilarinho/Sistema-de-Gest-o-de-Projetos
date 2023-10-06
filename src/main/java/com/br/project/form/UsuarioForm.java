package com.br.project.form;

import java.util.ArrayList;
import java.util.List;

import com.br.project.models.Usuario;

public class UsuarioForm {

	private String login;

	private String nome;

	private String senha;

	private String cpf;

	private String celular;

	private String email;

	private Boolean status;

	private Long idEmpresa;

	private List<Long> perfis = new ArrayList<>();

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public List<Long> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Long> perfis) {
		this.perfis = perfis;
	}

	public Usuario formulario() {

		return new Usuario(login, nome, senha, cpf, celular, email, status, idEmpresa);

	}

}
