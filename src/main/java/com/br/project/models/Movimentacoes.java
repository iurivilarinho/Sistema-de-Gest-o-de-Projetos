package com.br.project.models;

import java.time.LocalDateTime;

import com.br.project.models.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "tbMovimentacaoAtivo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Movimentacoes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Id_Ativo")
	private Ativo ativo;

	@ManyToOne
	@JoinColumn(name = "fk_Id_UsuarioEnvolvido")
	private Usuario usuarioEnvolvido;

	@Column(name = "tipo_movimentacao", nullable = false)
	private TipoMovimentacao tipo;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "data_Movimentacao")
	private LocalDateTime data;

	public Movimentacoes() {

	}

	public Movimentacoes(Ativo ativo, Usuario usuarioEnvolvido, TipoMovimentacao tipo, String observacao) {

		this.ativo = ativo;
		this.usuarioEnvolvido = usuarioEnvolvido;
		this.tipo = tipo;
		this.observacao = observacao;
		this.data = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public Usuario getUsuarioEnvolvido() {
		return usuarioEnvolvido;
	}

	public void setUsuarioEnvolvido(Usuario usuarioEnvolvido) {
		this.usuarioEnvolvido = usuarioEnvolvido;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public String getObservacao() {
		return observacao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
