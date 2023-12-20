package com.br.project.filtros;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.br.project.models.Projeto;
import com.br.project.models.enums.Status;
import com.br.project.repository.ProjetoRepository;
import com.br.project.specification.ProjetoSpecification;

@Service
public class ProjetoFiltros {

	@Autowired
	private ProjetoRepository projetoRepository;

	public Page<Projeto> filtro(String nome, LocalDate dataIni, LocalDate dataFim, Long idUsuario, List<Status> status,
			Status statusNot, Pageable paginacao) {

		Specification<Projeto> spec = Specification.where(null);

		if (nome != null) {
			spec = spec.and(ProjetoSpecification.nomeContem(nome));
		}
		if (dataIni != null && dataFim != null) {
			spec = spec.and(ProjetoSpecification.dataAberturaEntre(dataIni, dataFim));
		}
		if (idUsuario != null) {
			spec = spec.and(ProjetoSpecification.usuarioIgual(idUsuario));
		}
		if (status != null) {
			spec = spec.and(ProjetoSpecification.statusIgual(status));
		}

		if (statusNot != null) {
			spec = spec.and(ProjetoSpecification.statusDiferente(statusNot));
		}
		Page<Projeto> resultados = projetoRepository.findAll(spec, paginacao);

		return resultados;
	}
}
