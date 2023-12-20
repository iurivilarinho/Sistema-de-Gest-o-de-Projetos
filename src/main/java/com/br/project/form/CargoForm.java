package com.br.project.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CargoForm {

	@NotNull(message = "O campo nome não pode ser nulo!")
	@NotBlank(message = "O campo nome não pode ser vazio!")
	private String nome;

	@NotNull(message = "O campo descrição não pode ser nulo!")
	@NotBlank(message = "O campo descrição não pode ser vazio!")
	private String descricao;

	@NotNull(message = "O campo departamento não pode ser nulo!")
	@NotBlank(message = "O campo departamento não pode ser vazio!")
	private String departamento;

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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
