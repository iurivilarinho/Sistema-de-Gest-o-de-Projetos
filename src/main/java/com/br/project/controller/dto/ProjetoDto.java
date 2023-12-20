package com.br.project.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.br.project.models.Envolvidos;
import com.br.project.models.Pilar;
import com.br.project.models.Projeto;
import com.br.project.models.Usuario;
import com.br.project.models.enums.Sprint;
import com.br.project.models.enums.Status;

public class ProjetoDto {

	private Long id;
	private String nome;
	private Status status;
	private String descricao;
	private String justificativa;
	private Usuario usuario;
	private LocalDate dataFinalizacao;
	private LocalDate dataPrevista;
	private LocalDate dataLevantamento;
	private Sprint sprint;
	private List<Envolvidos> envolvidos = new ArrayList<>();
	private List<Pilar> pilares = new ArrayList<>();
	private List<DocumentoDto> documentos = new ArrayList<>();

	public ProjetoDto(Projeto projeto) {
		this.id = projeto.getId();
		this.nome = projeto.getNome();
		this.status = projeto.getStatus();
		this.descricao = projeto.getDescricao();
		this.justificativa = projeto.getJustificativa();
		this.usuario = projeto.getUsuario();
		this.dataFinalizacao = projeto.getDataFinalizacao();
		this.dataPrevista = projeto.getDataPrevista();
		this.dataLevantamento = projeto.getDataLevantamento();
		this.sprint = projeto.getSprint();
		this.envolvidos = projeto.getEnvolvidos();
		this.pilares = projeto.getPilares();
		this.documentos = projeto.getDocumentos().stream().map(documento -> new DocumentoDto(documento))
				.collect(Collectors.toList());
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

	public List<DocumentoDto> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoDto> documentos) {
		this.documentos = documentos;
	}

}
