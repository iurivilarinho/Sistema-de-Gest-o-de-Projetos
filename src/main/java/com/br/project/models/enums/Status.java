package com.br.project.models.enums;

public enum Status {

	FINALIZADO("Finalizado"), ANDAMENTO("Em Andamento"), CANCELADO("Cancelado"), APROVADO("Aprovado"),
	RECUSADO("Recusado"), PENDENTE_EXECUCAO("Pendente de Execução"), PENDENTE_APROVACAO("Pendente de Aprovação"),
	PENDENTE_REQUISITOS("Pendente de Requisitos");

	private final String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
