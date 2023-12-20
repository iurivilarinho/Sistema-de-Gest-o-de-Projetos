package com.br.project.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbEmpresas")
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 40)
	private String nome;
	@Column(name = "cnpj", length = 14)
	private String cnpj;
	@Column(name = "logradouro", length = 30)
	private String logradouro;
	@Column(name = "bairro", length = 30)
	private String bairro;
	@Column(name = "cidade", length = 25)
	private String cidade;
	@Column(name = "estado", length = 15)
	private String estado;
	@Column(name = "cep", length = 8)
	private String cep;
	@Column(name = "telefone", length = 11)
	private String telefone;
	@Column(name = "email", length = 30)
	private String email;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Id_UsuarioResponsavel")
	private Usuario usuarioResponsavel;

	public Empresa() {

	}

	public Empresa(String nome, String cnpj, String logradouro, String bairro, String cidade, String estado, String cep,
			String telefone, String email, Usuario usuarioResponsavel) {

		this.nome = nome;
		this.cnpj = cnpj;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
		this.usuarioResponsavel = usuarioResponsavel;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

}
