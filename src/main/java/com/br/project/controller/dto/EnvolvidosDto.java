package com.br.project.controller.dto;

import com.br.project.models.enums.Papel;

public class EnvolvidosDto {

	private Long idUsuario;

	private Papel papel;

	private Integer custoHora;

	public EnvolvidosDto(Long idUsuario, Papel papel, Integer custoHora) {

		this.idUsuario = idUsuario;
		this.papel = papel;
		this.custoHora = custoHora;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Integer getCustoHora() {
		return custoHora;
	}

	public void setCustoHora(Integer custoHora) {
		this.custoHora = custoHora;
	}

}
