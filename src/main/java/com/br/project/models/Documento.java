package com.br.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbDocumento")
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String contentType;

	@Lob
	private byte[] documento;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "idProjeto")
	private Projeto idProjeto;

	public Documento() {

	}

	public Documento(String nome, String contentType, byte[] documento, Projeto idProjeto) {

		this.nome = nome;
		this.contentType = contentType;
		this.documento = documento;
		this.idProjeto = idProjeto;
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public Projeto getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Projeto idProjeto) {
		this.idProjeto = idProjeto;
	}

}