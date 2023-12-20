package com.br.project.models.enums;

public enum TipoMovimentacao {
	ENTREGA("Entrega"), DEVOLUCAO("Devolução"), MANUTENCAO("Manutenção"), DESATIVACAO("Desativação"),
	CADASTRO("Cadastro"), ATUALIZACAO("Atualização"), ATIVACAO("Ativação");

	private final String descricao;

	TipoMovimentacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
