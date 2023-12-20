package com.br.project.form;

import java.util.ArrayList;
import java.util.List;

import com.br.project.controller.dto.DocumentoDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtivoForm {

	@Size(max = 50, message = "O campo 'tag' deve ter no máximo 50 caracteres.")
	private String tag;

	@Size(max = 20, message = "O campo 'codChip' deve ter no máximo 20 caracteres.")
	private String codChip;

	@Size(max = 20, message = "O campo 'imei' deve ter no máximo 20 caracteres.")
	private String imei;

	@Size(max = 50, message = "O campo 'marca' deve ter no máximo 50 caracteres.")
	private String marca;

	@Size(max = 255, message = "O campo 'keyLicenca' deve ter no máximo 255 caracteres.")
	private String keyLicenca;

	@Size(max = 20, message = "O campo 'mac' deve ter no máximo 20 caracteres.")
	private String mac;

	@Size(max = 20, message = "O campo 'numChip' deve ter no máximo 20 caracteres.")
	private String numChip;

	@Size(max = 50, message = "O campo 'processador' deve ter no máximo 50 caracteres.")
	private String processador;

	@Size(max = 20, message = "O campo 'ram' deve ter no máximo 20 caracteres.")
	private String ram;

	@Size(max = 50, message = "O campo 'condicao' deve ter no máximo 50 caracteres.")
	private String condicao;

	@Size(max = 1000, message = "O campo 'observacao' deve ter no máximo 1000 caracteres.")
	private String observacao;

	@Size(max = 50, message = "O campo 'modelo' deve ter no máximo 50 caracteres.")
	private String modelo;

	@Size(max = 50, message = "O campo 'armazenamento' deve ter no máximo 50 caracteres.")
	private String armazenamento;

	// IDs podem ser nulos
	private Long idUsuarioVinculado;

	@NotNull(message = "O campo idTipo não pode ser nulo!")
	private Long idTipo;

	private List<DocumentoDto> imagens = new ArrayList<>();

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

	public Long getIdUsuarioVinculado() {
		return idUsuarioVinculado;
	}

	public void setIdUsuarioVinculado(Long idUsuarioVinculado) {
		this.idUsuarioVinculado = idUsuarioVinculado;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public List<DocumentoDto> getImagens() {
		return imagens;
	}

	public void setImagens(List<DocumentoDto> imagens) {
		this.imagens = imagens;
	}

}
