package com.br.project.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.br.project.models.Ativo;
import com.br.project.models.Usuario;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.persistence.metamodel.Attribute;

public class UsuarioSpecification {

	public static Specification<Usuario> searchAllFields(String searchTerm) {
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

	public static Specification<Usuario> vinculados(Boolean vinculados) {
		return (root, query, builder) -> {
			Subquery<Long> subquery = query.subquery(Long.class);
			Root<Ativo> ativoRoot = subquery.from(Ativo.class);

			subquery.select(builder.literal(1L)).where(builder.equal(ativoRoot.get("usuarioVinculado"), root));

			if (vinculados) {
				return builder.exists(subquery);
			} else {
				return builder.not(builder.exists(subquery));
			}
		};
	}

}
