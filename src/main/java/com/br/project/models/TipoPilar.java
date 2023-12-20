package com.br.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbTipoPilar")
public class TipoPilar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Boolean usaHora;

	private Boolean usaValor;

	private Boolean usaSubPilar;

	private Boolean usaDescricao;

	private String tipo;

	public TipoPilar() {

	}

	public TipoPilar(String nome, Boolean usaHora, Boolean usaValor, Boolean usaSubPilar, Boolean usaDescricao,
			String tipo) {
		this.nome = nome;
		this.usaHora = usaHora;
		this.usaValor = usaValor;
		this.usaSubPilar = usaSubPilar;
		this.usaDescricao = usaDescricao;
		this.tipo = tipo.toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getUsaHora() {
		return usaHora;
	}

	public void setUsaHora(Boolean usaHora) {
		this.usaHora = usaHora;
	}

	public Boolean getUsaValor() {
		return usaValor;
	}

	public void setUsaValor(Boolean usaValor) {
		this.usaValor = usaValor;
	}

	public Boolean getUsaSubPilar() {
		return usaSubPilar;
	}

	public void setUsaSubPilar(Boolean usaSubPilar) {
		this.usaSubPilar = usaSubPilar;
	}

	public Boolean getUsaDescricao() {
		return usaDescricao;
	}

	public void setUsaDescricao(Boolean usaDescricao) {
		this.usaDescricao = usaDescricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}

}
