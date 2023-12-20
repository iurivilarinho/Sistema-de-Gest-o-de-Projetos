package com.br.project.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tbAtivo", uniqueConstraints = @UniqueConstraint(columnNames = { "tag" }))
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Ativo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tag", length = 50)
	private String tag;

	@Column(name = "cod_chip", length = 20)
	private String codChip;

	@Column(name = "imei", length = 20)
	private String imei;

	@Column(name = "marca", length = 50)
	private String marca;

	@Column(name = "key_licenca", length = 255)
	private String keyLicenca;

	@Column(name = "mac", length = 20)
	private String mac;

	@Column(name = "num_chip", length = 20)
	private String numChip;

	@Column(name = "status", nullable = false)
	private Boolean status;

	@Column(name = "processador", length = 50)
	private String processador;

	@Column(name = "ram", length = 20)
	private String ram;

	@Column(name = "condicao", length = 50)
	private String condicao;

	@Column(name = "observacao", length = 1000)
	private String observacao;

	@Column(name = "modelo", length = 50)
	private String modelo;

	@Column(name = "armazenamento", length = 50)
	private String armazenamento;

	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuarioVinculado;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Id_Tipo")
	private TipoAtivo tipo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tbAtivos_X_Documentos", joinColumns = @JoinColumn(name = "fk_Id_Ativo"), inverseJoinColumns = @JoinColumn(name = "fk_Id_Documento"))
	private Set<Documento> imagens = new HashSet<>();

	@OneToMany(mappedBy = "ativo", cascade = CascadeType.ALL)
	private List<Movimentacoes> movimentacoes = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Id_DocumentoFiscal")
	private Documento notaFiscal;

	@Column(name = "dataCadastro")
	private LocalDateTime dataCadastro;

	public Ativo() {

	}

	public Ativo(String tag, String codChip, String imei, String marca, String keyLicenca, String mac, String numChip,
			String processador, String ram, String condicao, String observacao, String modelo, String armazenamento,
			Usuario usuarioVinculado, TipoAtivo tipo, Documento notaFiscal) {

		this.tag = tag;
		this.codChip = codChip;
		this.imei = imei;
		this.marca = marca;
		this.keyLicenca = keyLicenca;
		this.mac = mac;
		this.numChip = numChip;
		this.status = true;
		this.processador = processador;
		this.ram = ram;
		this.condicao = condicao;
		this.observacao = observacao;
		this.modelo = modelo;
		this.armazenamento = armazenamento;
		this.usuarioVinculado = usuarioVinculado;
		this.tipo = tipo;
		this.dataCadastro = LocalDateTime.now();
		this.notaFiscal = notaFiscal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCodChip() {
		return codChip;
	}

	public void setCodChip(String codChip) {
		this.codChip = codChip;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getKeyLicenca() {
		return keyLicenca;
	}

	public void setKeyLicenca(String keyLicenca) {
		this.keyLicenca = keyLicenca;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getNumChip() {
		return numChip;
	}

	public void setNumChip(String numChip) {
		this.numChip = numChip;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getProcessador() {
		return processador;
	}

	public void setProcessador(String processador) {
		this.processador = processador;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getArmazenamento() {
		return armazenamento;
	}

	public void setArmazenamento(String armazenamento) {
		this.armazenamento = armazenamento;
	}

	public Usuario getUsuarioVinculado() {
		return usuarioVinculado;
	}

	public void setUsuarioVinculado(Usuario usuarioVinculado) {
		this.usuarioVinculado = usuarioVinculado;
	}

	public Documento getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Documento notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public TipoAtivo getTipo() {
		return tipo;
	}

	public List<Movimentacoes> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacoes> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public void setTipo(TipoAtivo tipo) {
		this.tipo = tipo;
	}

	public Set<Documento> getImagens() {
		return imagens;
	}

	public void setImagens(Set<Documento> imagens) {
		this.imagens = imagens;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
