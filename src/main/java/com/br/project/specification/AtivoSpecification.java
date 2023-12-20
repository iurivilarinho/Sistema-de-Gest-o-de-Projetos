package com.br.project.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.br.project.models.Ativo;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.Attribute;

public class AtivoSpecification {

	public static Specification<Ativo> buscarAtivoPorUsuarioVinculado(Long idUsuario) {
		return (root, query, builder) -> builder.equal(root.get("usuarioVinculado").get("id"), idUsuario);
	}

	public static Specification<Ativo> buscarAtivoTipo(Long idTipo) {
		return (root, query, builder) -> builder.equal(root.get("tipo").get("id"), idTipo);
	}

	public static Specification<Ativo> buscarAtivoPorTag(String tag) {
		return (root, query, builder) -> builder.equal(root.get("tag"), tag);
	}

	public static Specification<Ativo> statusIgual(Boolean status) {
		return (root, query, builder) -> builder.equal(root.get("status"), status);
	}

	public static Specification<Ativo> porVinculacao(Boolean vinculado) {
		if (vinculado) {
			return (root, query, builder) -> builder.isNotNull(root.get("usuarioVinculado"));
		} else {
			return (root, query, builder) -> builder.isNull(root.get("usuarioVinculado"));

		}
	}

	public static Specification<Ativo> searchAllFields(String searchTerm) {
		return (root, query, criteriaBuilder) -> {
			// Lista de expressões para armazenar as condições para cada campo
			List<Predicate> predicates = new ArrayList<>();

			// Obtém todos os atributos da entidade
			for (Attribute<?, ?> attribute : root.getModel().getAttributes()) {
				if (attribute.getJavaType().equals(String.class)) {
					// Se o atributo for do tipo String, adicione uma condição de like
					predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(attribute.getName())),
							"%" + searchTerm.toLowerCase() + "%"));
				}
			}

			// Cria uma condição OR com as expressões de cada campo
			return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
		};
	}

}
