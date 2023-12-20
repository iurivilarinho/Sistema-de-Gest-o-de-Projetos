package com.br.project.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.br.project.models.TipoPilar;
import com.br.project.models.enums.SubPilar;

public class PilarDto {

	private String descricao;

	private List<SubPilar> subPilar = new ArrayList<>();

	private LocalDateTime dataCadastro = LocalDateTime.now();

	private BigDecimal valor;

	private Integer horas;

	private TipoPilar tipo;

	public PilarDto(String descricao, List<SubPilar> subPilar, LocalDateTime dataCadastro, BigDecimal valor,
			Integer horas, TipoPilar tipo) {

		this.descricao = descricao;
		this.subPilar = subPilar;
		this.dataCadastro = dataCadastro;
		this.valor = valor;
		this.horas = horas;
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<SubPilar> getSubPilar() {
		return subPilar;
	}

	public void setSubPilar(List<SubPilar> subPilar) {
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

	public TipoPilar getTipo() {
		return tipo;
	}

	public void setTipo(TipoPilar tipo) {
		this.tipo = tipo;
	}

}
