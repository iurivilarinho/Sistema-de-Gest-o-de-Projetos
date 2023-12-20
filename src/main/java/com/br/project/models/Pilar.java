package com.br.project.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.br.project.models.enums.SubPilar;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
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

	@ElementCollection(targetClass = SubPilar.class)
	@Enumerated(EnumType.STRING)
	private List<SubPilar> subPilar = new ArrayList<>();

	private LocalDateTime dataCadastro = LocalDateTime.now();

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idProjeto", foreignKey = @ForeignKey(name = "FK_FROM_PROJETO_x_PILAR"))
	private Projeto projeto;

	private BigDecimal valor;

	private Integer horas;

	@ManyToOne
	@JoinColumn(name = "idTipoPilar")
	private TipoPilar tipo;

	public Pilar() {

	}

	public Pilar(String descricao, List<SubPilar> subPilar, Projeto projeto, BigDecimal valor, Integer horas,
			TipoPilar tipo) {

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
