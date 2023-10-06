package com.br.project.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.br.project.controller.dto.EnvolvidosDto;
import com.br.project.controller.dto.PilarDto;
import com.br.project.models.Projeto;
import com.br.project.models.Usuario;
import com.br.project.models.enums.Status;

public class ProjetoForm {

	private String nome;
	private Status situation;
	private String descricao;
	private String justificativa;
	private List<EnvolvidosDto> envolvidos;
	private List<PilarDto> pilares = new ArrayList<>();
	private List<MultipartFile> documentos = new ArrayList<>();

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

	public List<MultipartFile> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<MultipartFile> documentos) {
		this.documentos = documentos;
	}

	public Projeto cadastrar(Usuario usuario) {

		return new Projeto(nome, situation, justificativa, usuario, descricao);
	}

}
