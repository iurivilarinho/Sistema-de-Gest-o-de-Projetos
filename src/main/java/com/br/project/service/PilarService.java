package com.br.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.project.controller.dto.PilarDto;
import com.br.project.models.Pilar;
import com.br.project.models.Projeto;
import com.br.project.models.TipoPilar;
import com.br.project.repository.PilarRepository;
import com.br.project.repository.TipoPilarRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PilarService {

	@Autowired
	private TipoPilarRepository tipoPilarRepository;

	@Autowired
	private PilarRepository pilarRepository;

	@Autowired
	private EntityManager entity;

	@Transactional(readOnly = true)
	public TipoPilar buscarTipoPilarPorId(Long id) {
		return tipoPilarRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Tipo de Pilar não encontrado para o ID: " + id));
	}

	@Transactional
	public void salvarPilares(List<PilarDto> pilares, Projeto projeto) {
		pilarRepository.saveAll(pilares.stream().map(
				p -> new Pilar(p.getDescricao(), p.getSubPilar(), projeto, p.getValor(), p.getHoras(), p.getTipo()))
				.collect(Collectors.toList()));
	}

	@Transactional(readOnly = true)
	public Pilar buscarPilarPorProjeto(Long idProjeto) {
		return pilarRepository.findByProjeto_Id(idProjeto).orElseThrow(
				() -> new EntityNotFoundException("Pilar não encontrada para o ID de projeto : " + idProjeto));
	}

	@Transactional
	public void apagar(Long idProjeto) {
		buscarPilarPorProjeto(idProjeto);
		pilarRepository.deleteByProjeto_Id(idProjeto);
		entity.flush();
	}
}
