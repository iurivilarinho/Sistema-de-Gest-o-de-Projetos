package com.br.project.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TipoAtivoForm {

	@NotBlank(message = "O nome não pode ser nulo ou vazio")
	private String nome;

	@NotNull(message = "O campo usaCodChip não pode ser nulo")
	private Boolean usaCodChip;

	@NotNull(message = "O campo usaImei não pode ser nulo")
	private Boolean usaImei;

	@NotNull(message = "O campo usaMarca não pode ser nulo")
	private Boolean usaMarca;

	@NotNull(message = "O campo usaKeyLicenca não pode ser nulo")
	private Boolean usaKeyLicenca;

	@NotNull(message = "O campo usaMac não pode ser nulo")
	private Boolean usaMac;

	@NotNull(message = "O campo usaNumChip não pode ser nulo")
	private Boolean usaNumChip;

	@NotNull(message = "O campo usaProcessador não pode ser nulo")
	private Boolean usaProcessador;

	@NotNull(message = "O campo usaRam não pode ser nulo")
	private Boolean usaRam;

	@NotNull(message = "O campo usaCondicao não pode ser nulo")
	private Boolean usaCondicao;

	@NotNull(message = "O campo usaArmazenamento não pode ser nulo")
	private Boolean usaArmazenamento;

	@NotNull(message = "O campo usaModelo não pode ser nulo")
	private Boolean usaModelo;

	@NotNull(message = "O campo usaVinculo não pode ser nulo")
	private Boolean usaVinculo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getUsaCodChip() {
		return usaCodChip;
	}

	public void setUsaCodChip(Boolean usaCodChip) {
		this.usaCodChip = usaCodChip;
	}

	public Boolean getUsaImei() {
		return usaImei;
	}

	public void setUsaImei(Boolean usaImei) {
		this.usaImei = usaImei;
	}

	public Boolean getUsaMarca() {
		return usaMarca;
	}

	public void setUsaMarca(Boolean usaMarca) {
		this.usaMarca = usaMarca;
	}

	public Boolean getUsaKeyLicenca() {
		return usaKeyLicenca;
	}

	public void setUsaKeyLicenca(Boolean usaKeyLicenca) {
		this.usaKeyLicenca = usaKeyLicenca;
	}

	public Boolean getUsaMac() {
		return usaMac;
	}

	public void setUsaMac(Boolean usaMac) {
		this.usaMac = usaMac;
	}

	public Boolean getUsaNumChip() {
		return usaNumChip;
	}

	public void setUsaNumChip(Boolean usaNumChip) {
		this.usaNumChip = usaNumChip;
	}

	public Boolean getUsaProcessador() {
		return usaProcessador;
	}

	public void setUsaProcessador(Boolean usaProcessador) {
		this.usaProcessador = usaProcessador;
	}

	public Boolean getUsaRam() {
		return usaRam;
	}

	public void setUsaRam(Boolean usaRam) {
		this.usaRam = usaRam;
	}

	public Boolean getUsaCondicao() {
		return usaCondicao;
	}

	public void setUsaCondicao(Boolean usaCondicao) {
		this.usaCondicao = usaCondicao;
	}

	public Boolean getUsaArmazenamento() {
		return usaArmazenamento;
	}

	public void setUsaArmazenamento(Boolean usaArmazenamento) {
		this.usaArmazenamento = usaArmazenamento;
	}

	public Boolean getUsaModelo() {
		return usaModelo;
	}

	public void setUsaModelo(Boolean usaModelo) {
		this.usaModelo = usaModelo;
	}

	public Boolean getUsaVinculo() {
		return usaVinculo;
	}

	public void setUsaVinculo(Boolean usaVinculo) {
		this.usaVinculo = usaVinculo;
	}

}
