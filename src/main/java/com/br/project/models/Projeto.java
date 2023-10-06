package com.br.project.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.br.project.models.enums.Sprint;
import com.br.project.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbProjeto")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Status situation;

	private String descricao;

	private String justificativa;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	private LocalDateTime dataCriacao = LocalDateTime.now();

	private LocalDateTime dataFinalizacao;

	private LocalDate dataPrevista;

	private Sprint sprint;

	@OneToMany(mappedBy = "projeto")
	private List<Envolvidos> envolvidos = new ArrayList<>();

	@OneToMany(mappedBy = "projeto")
	private List<Pilar> pilares = new ArrayList<>();

	@OneToMany(mappedBy = "idProjeto", cascade = CascadeType.ALL)
	private List<Documento> documentos = new ArrayList<>();

	public Projeto() {

	}

	public Projeto(String nome, Status situation, String descricao, String justificativa, Usuario usuario,
			LocalDateTime dataCriacao, LocalDateTime dataFinalizacao, LocalDate dataPrevista, Sprint sprint,
			List<Envolvidos> envolvidos, List<Pilar> pilares, List<Documento> documentos) {

		this.nome = nome;
		this.situation = situation;
		this.descricao = descricao;
		this.justificativa = justificativa;
		this.usuario = usuario;
		this.dataCriacao = dataCriacao;
		this.dataFinalizacao = dataFinalizacao;
		this.dataPrevista = dataPrevista;
		this.sprint = sprint;
		this.envolvidos = envolvidos;
		this.pilares = pilares;
		this.documentos = documentos;
	}

	public Projeto(String nome, Status situation, String justificativa, Usuario usuario, String descricao) {
		this.nome = nome;
		this.situation = situation;
		this.justificativa = justificativa;
		this.usuario = usuario;
		this.descricao = descricao;
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

	public Status getSituation() {
		return situation;
	}

	public void setSituation(Status situation) {
		this.situation = situation;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public LocalDate getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public List<Envolvidos> getEnvolvidos() {
		return envolvidos;
	}

	public void setEnvolvidos(List<Envolvidos> envolvidos) {
		this.envolvidos = envolvidos;
	}

	public List<Pilar> getPilares() {
		return pilares;
	}

	public void setPilares(List<Pilar> pilares) {
		this.pilares = pilares;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

}
