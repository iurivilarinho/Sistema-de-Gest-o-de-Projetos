package com.br.project.authentication;

import com.br.project.models.Usuario;

public class TokenDto {

	private String token;
	private String tipo;
	private Usuario usuario;

	public TokenDto(String token, String tipo, Usuario usuario) {
		this.token = token;
		this.tipo = tipo;
		this.usuario = usuario;

	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario idUsuario) {
		this.usuario = idUsuario;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
