package com.br.project.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.br.project.controller.dto.DocumentoDto;
import com.br.project.controller.dto.EnvolvidosDto;
import com.br.project.controller.dto.PilarDto;
import com.br.project.models.enums.Sprint;

public class ProjetoForm {

	private String nome;
	private String descricao;
	private Sprint sprint;
	private String justificativa;
	private LocalDate dataPrevista;
	private LocalDate dataLevantamento;
	private List<String> linkDocs = new ArrayList<>();
	private List<EnvolvidosDto> envolvidos;
	private List<PilarDto> pilares = new ArrayList<>();
	private List<DocumentoDto> documentos = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public List<EnvolvidosDto> getEnvolvidos() {
		return envolvidos;
	}

	public void setEnvolvidos(List<EnvolvidosDto> envolvidos) {
		this.envolvidos = envolvidos;
	}

	public List<PilarDto> getPilares() {
		return pilares;
	}

	public void setPilares(List<PilarDto> pilares) {
		this.pilares = pilares;
	}

	public List<DocumentoDto> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoDto> documentos) {
		this.documentos = documentos;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
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

	public List<String> getLinkDocs() {
		return linkDocs;
	}

	public void setLinkDocs(List<String> linkDocs) {
		this.linkDocs = linkDocs;
	}

}
