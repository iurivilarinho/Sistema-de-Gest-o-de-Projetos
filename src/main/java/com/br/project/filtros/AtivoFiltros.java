package com.br.project.filtros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.br.project.models.Ativo;
import com.br.project.repository.AtivoRepository;
import com.br.project.specification.AtivoSpecification;

@Service
public class AtivoFiltros {

	@Autowired
	private AtivoRepository ativoRepository;

	public Page<Ativo> filtro(String tag, Boolean status, String search, Long idUsuario, Long idTipo, Boolean vinculado,
			Pageable paginacao) {
		Specification<Ativo> spec = Specification.where(null);

		if (tag != null) {
			spec = spec.and(AtivoSpecification.buscarAtivoPorTag(tag));
		}

		if (status != null) {
			spec = spec.and(AtivoSpecification.statusIgual(status));
		}

		if (search != null) {
			spec = spec.and(AtivoSpecification.searchAllFields(search));
		}

		if (idUsuario != null) {
			spec = spec.and(AtivoSpecification.buscarAtivoPorUsuarioVinculado(idUsuario));
		}

		if (idTipo != null) {
			spec = spec.and(AtivoSpecification.buscarAtivoTipo(idTipo));
		}

		if (vinculado != null) {
			spec = spec.and(AtivoSpecification.porVinculacao(vinculado));
		}

		Page<Ativo> resultados = ativoRepository.findAll(spec, paginacao);

		return resultados;

	}
}
