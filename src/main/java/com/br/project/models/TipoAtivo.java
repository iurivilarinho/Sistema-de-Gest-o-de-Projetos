package com.br.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbTipoAtivo")
public class TipoAtivo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "usa_cod_chip", nullable = false)
	private Boolean usaCodChip;

	@Column(name = "usa_imei", nullable = false)
	private Boolean usaImei;

	@Column(name = "usa_marca", nullable = false)
	private Boolean usaMarca;

	@Column(name = "usa_key_licenca", nullable = false)
	private Boolean usaKeyLicenca;

	@Column(name = "usa_mac", nullable = false)
	private Boolean usaMac;

	@Column(name = "usa_num_chip", nullable = false)
	private Boolean usaNumChip;

	@Column(name = "usa_processador", nullable = false)
	private Boolean usaProcessador;

	@Column(name = "usa_ram", nullable = false)
	private Boolean usaRam;

	@Column(name = "usa_condicao", nullable = false)
	private Boolean usaCondicao;

	@Column(name = "usa_armazenamento", nullable = false)
	private Boolean usaArmazenamento;

	@Column(name = "usa_modelo", nullable = false)
	private Boolean usaModelo;

	@Column(name = "usa_vinculo", nullable = false)
	private Boolean usaVinculo;

	public TipoAtivo() {

	}

	public TipoAtivo(String nome, Boolean usaCodChip, Boolean usaImei, Boolean usaMarca, Boolean usaKeyLicenca,
			Boolean usaMac, Boolean usaNumChip, Boolean usaProcessador, Boolean usaRam, Boolean usaCondicao,
			Boolean usaArmazenamento, Boolean usaModelo, Boolean usaVinculo) {

		this.nome = nome;
		this.usaCodChip = usaCodChip;
		this.usaImei = usaImei;
		this.usaMarca = usaMarca;
		this.usaKeyLicenca = usaKeyLicenca;
		this.usaMac = usaMac;
		this.usaNumChip = usaNumChip;
		this.usaProcessador = usaProcessador;
		this.usaRam = usaRam;
		this.usaCondicao = usaCondicao;
		this.usaArmazenamento = usaArmazenamento;
		this.usaModelo = usaModelo;
		this.usaVinculo = usaVinculo;
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
