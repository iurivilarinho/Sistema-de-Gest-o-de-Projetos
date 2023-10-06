package com.br.project.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.br.project.models.enums.SubPilar;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbPilar")
public class Pilar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private SubPilar subPilar;

	private LocalDateTime dataCadastro = LocalDateTime.now();

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idProjeto")
	private Projeto projeto;

	private BigDecimal valor;

	private Integer horas;

	@ManyToOne
	@JoinColumn(name = "idTipoPilar")
	private TipoPilar tipo;

	public Pilar() {

	}

	public Pilar(String descricao, SubPilar subPilar,  Projeto projeto, BigDecimal valor,
			Integer horas, TipoPilar tipo) {

		this.descricao = descricao;
		this.subPilar = subPilar;
		this.projeto = projeto;
		this.valor = valor;
		this.horas = horas;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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
