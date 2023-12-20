package com.br.project.controller.dto;

import java.util.HashSet;
import java.util.Set;

import com.br.project.models.Documento;
import com.br.project.models.Usuario;
import com.br.project.models.acesso.Perfil;

public class UsuarioDto {

	private Long id;
	private String login;
	private String nome;
	private String senha;
	private String cpf;
	private String celular;
	private String email;
	private Boolean status;
	private Documento imagem;
	private Set<Perfil> perfis = new HashSet<>();

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.cpf = usuario.getCpf();
		this.celular = usuario.getCelular();
		this.email = usuario.getEmail();
		this.status = usuario.getStatus();
		this.perfis = usuario.getPerfis();
		this.imagem = usuario.getImagem();
	}

	public String getLogin() {
		return login;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCelular() {
		return celular;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getStatus() {
		return status;
	}

	public Documento getImagem() {
		return imagem;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public Long getId() {
		return id;
	}

}
