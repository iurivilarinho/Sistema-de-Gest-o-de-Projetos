package com.br.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.project.controller.dto.EnvolvidosDto;
import com.br.project.models.Envolvidos;
import com.br.project.models.Projeto;
import com.br.project.repository.EnvolvidosRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnvolvidosService {

	@Autowired
	private EnvolvidosRepository envolvidosRepository;

	@Autowired
	private UsuarioService usuarioService;

	private EntityManager entity;

	@Transactional
	public void salvarEnvolvidos(List<EnvolvidosDto> envolvidos, Projeto projeto) {
		envolvidosRepository.saveAll(
				envolvidos.stream().map(e -> new Envolvidos(usuarioService.buscarUsuarioPorId(e.getIdUsuario()),
						e.getPapel(), e.getCustoHora(), projeto)).collect(Collectors.toList()));

	}

	@Transactional(readOnly = true)
	public Envolvidos buscarEnvolvidosPorProjeto(Long idProjeto) {
		return envolvidosRepository.findByProjeto_Id(idProjeto).orElseThrow(
				() -> new EntityNotFoundException("Envolvidos não encontrada para o ID de projeto : " + idProjeto));
	}

	@Transactional
	public void apagar(Long idProjeto) {
		buscarEnvolvidosPorProjeto(idProjeto);
		envolvidosRepository.deleteByProjeto_Id(idProjeto);
		entity.flush();
	}

}
