package com.br.project.models;

import com.br.project.models.enums.Papel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbEnvolvidos")
public class Envolvidos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	private Papel papel;

	private Integer custoHora;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idProjeto")
	private Projeto projeto;

	public Envolvidos() {

	}

	public Envolvidos(Usuario usuario, Papel papel, Integer custoHora, Projeto projeto) {

		this.usuario = usuario;
		this.papel = papel;
		this.custoHora = custoHora;
		this.projeto = projeto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

}
