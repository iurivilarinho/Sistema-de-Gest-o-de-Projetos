package com.br.project.specification;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.br.project.models.Projeto;
import com.br.project.models.enums.Status;

public class ProjetoSpecification {

	public static Specification<Projeto> statusIgual(List<Status> status) {
		return (root, query, builder) -> root.get("status").in(status);
	}

	public static Specification<Projeto> statusDiferente(Status status) {
		return (root, query, builder) -> builder.notEqual(root.get("status"), status);
	}

	public static Specification<Projeto> usuarioIgual(Long idUsuario) {
		return (root, query, builder) -> builder.equal(root.get("usuario").get("id"), idUsuario);
	}

	public static Specification<Projeto> nomeContem(String nome) {
		return (root, query, builder) -> builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
	}

	public static Specification<Projeto> dataAberturaEntre(LocalDate dataIni, LocalDate dataFim) {
		return (root, query, builder) -> builder.between(root.get("dataCriacao"), dataIni, dataFim);
	}

}
