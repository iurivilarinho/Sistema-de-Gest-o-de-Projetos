package com.br.project.form;

import jakarta.validation.constraints.NotNull;

public class RelatorioAtivoForm {

	@NotNull(message = "O campo idAtivo não pode ser nulo!")
	private Long idAtivo;
	@NotNull(message = "O campo idUsuario não pode ser nulo!")
	private Long idUsuario;
	@NotNull(message = "O campo bateria não pode ser nulo!")
	private Boolean bateria;
	@NotNull(message = "O campo pelicula não pode ser nulo!")
	private Boolean pelicula;
	@NotNull(message = "O caixa idAtivo não pode ser nulo!")
	private Boolean caixa;
	@NotNull(message = "O carregador idAtivo não pode ser nulo!")
	private Boolean carregador;
	@NotNull(message = "O capinha idAtivo não pode ser nulo!")
	private Boolean capinha;
	@NotNull(message = "O concervado idAtivo não pode ser nulo!")
	private Boolean concervado;

	public Long getIdAtivo() {
		return idAtivo;
	}

	public void setIdAtivo(Long idAtivo) {
		this.idAtivo = idAtivo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Boolean getBateria() {
		return bateria;
	}

	public void setBateria(Boolean bateria) {
		this.bateria = bateria;
	}

	public Boolean getPelicula() {
		return pelicula;
	}

	public void setPelicula(Boolean pelicula) {
		this.pelicula = pelicula;
	}

	public Boolean getCaixa() {
		return caixa;
	}

	public void setCaixa(Boolean caixa) {
		this.caixa = caixa;
	}

	public Boolean getCarregador() {
		return carregador;
	}

	public void setCarregador(Boolean carregador) {
		this.carregador = carregador;
	}

	public Boolean getCapinha() {
		return capinha;
	}

	public void setCapinha(Boolean capinha) {
		this.capinha = capinha;
	}

	public Boolean getConcervado() {
		return concervado;
	}

	public void setConcervado(Boolean concervado) {
		this.concervado = concervado;
	}

}
