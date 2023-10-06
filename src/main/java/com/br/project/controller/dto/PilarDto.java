package com.br.project.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.project.models.enums.SubPilar;

public class PilarDto {

	private String descricao;

	private SubPilar subPilar;

	private LocalDateTime dataCadastro = LocalDateTime.now();

	private BigDecimal valor;

	private Integer horas;

	private Long idTipo;

	public PilarDto(String descricao, SubPilar subPilar, LocalDateTime dataCadastro, BigDecimal valor, Integer horas,
			Long idTipo) {

		this.descricao = descricao;
		this.subPilar = subPilar;
		this.dataCadastro = dataCadastro;
		this.valor = valor;
		this.horas = horas;
		this.idTipo = idTipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SubPilar getSubPilar() {
		return subPilar;
	}

	public void setSubPilar(SubPilar subPilar) {
		this.subPilar = subPilar;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

}
