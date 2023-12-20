package com.br.project.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.br.project.models.enums.Sprint;
import com.br.project.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

	private Status status;

	@Column(length = 1000)
	private String descricao;

	private String justificativa;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	private LocalDate dataCriacao = LocalDate.now();

	private LocalDate dataFinalizacao;

	private LocalDate dataPrevista;

	private LocalDate dataLevantamento;

	private Sprint sprint;

	@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
	private List<Envolvidos> envolvidos = new ArrayList<>();

	@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pilar> pilares = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "tbProjeto_x_Documento", joinColumns = @JoinColumn(name = "id_Projeto"), inverseJoinColumns = @JoinColumn(name = "id_Documento"))
	private Set<Documento> documentos = new HashSet<>();

	private Integer pontos;

	@ElementCollection
	@CollectionTable(name = "tbProjeto_links", joinColumns = @JoinColumn(name = "projeto_id"))
	@Column(name = "linkDoc")
	private List<String> linkDocs = new ArrayList<>();

	public Projeto() {

	}

	public Projeto(String nome, Status status, String descricao, String justificativa, Usuario usuario,
			List<Envolvidos> envolvidos, List<Pilar> pilares, Set<Documento> documentos, List<String> linkDocs) {

		this.nome = nome;
		this.status = status;
		this.descricao = descricao;
		this.justificativa = justificativa;
		this.usuario = usuario;
		this.envolvidos = envolvidos;
		this.pilares = pilares;
		this.linkDocs = linkDocs;
		this.documentos = documentos;
	}

	public Projeto(String nome, Status status, String justificativa, Usuario usuario, String descricao,
			Set<Documento> documentos, List<String> linkDocs) {
		this.nome = nome;
		this.status = status;
		this.justificativa = justificativa;
		this.usuario = usuario;
		this.descricao = descricao;
		this.linkDocs = linkDocs;
		this.documentos = documentos;
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
		return status;
	}

	public void setSituation(Status situation) {
		this.status = situation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDate dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public LocalDate getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public LocalDate getDataLevantamento() {
		return dataLevantamento;
	}

	public void setDataLevantamento(LocalDate dataLevantamento) {
		this.dataLevantamento = dataLevantamento;
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

	public Set<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public List<String> getLinkDocs() {
		return linkDocs;
	}

	public void setLinkDocs(List<String> linkDocs) {
		this.linkDocs = linkDocs;
	}

}
