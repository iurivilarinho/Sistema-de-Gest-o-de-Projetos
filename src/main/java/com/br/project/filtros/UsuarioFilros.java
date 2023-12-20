package com.br.project.filtros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.br.project.models.Usuario;
import com.br.project.repository.UsuarioRepository;
import com.br.project.specification.UsuarioSpecification;

@Service
public class UsuarioFilros {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<Usuario> filtro(Boolean vinculados, String search, Pageable paginacao) {
		Specification<Usuario> spec = Specification.where(null);

		if (vinculados != null) {
			spec = spec.and(UsuarioSpecification.vinculados(vinculados));
		}

		if (search != null) {
			spec = spec.and(UsuarioSpecification.searchAllFields(search));
		}

		Page<Usuario> resultados = usuarioRepository.findAll(spec, paginacao);

		return resultados;

	}
}
