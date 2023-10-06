package com.br.project.authentication;

public class TokenDto {

	private String token;
	private String tipo;
	private Long idUsuario;

	public TokenDto(String token, String tipo, Long idUsuario) {
		this.token = token;
		this.tipo = tipo;
		this.idUsuario = idUsuario;

	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
