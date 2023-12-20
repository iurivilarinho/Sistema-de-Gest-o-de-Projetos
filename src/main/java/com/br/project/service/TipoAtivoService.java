package com.br.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.project.form.TipoAtivoForm;
import com.br.project.models.TipoAtivo;
import com.br.project.repository.TipoAtivoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoAtivoService {

	@Autowired
	private TipoAtivoRepository tipoAtivoRepository;

	@Transactional(readOnly = true)
	public TipoAtivo buscarTipoAtivoPorId(Long idTipoAtivo) {
		return tipoAtivoRepository.findById(idTipoAtivo).orElseThrow(
				() -> new EntityNotFoundException("Tipo de Ativo não encontrado para o ID: " + idTipoAtivo));
	}

	@Transactional(readOnly = true)
	public Page<TipoAtivo> buscar(Pageable paginacao) {
		return tipoAtivoRepository.findAll(paginacao);
	}

	@Transactional
	public TipoAtivo cadastrar(TipoAtivoForm form) {
		TipoAtivo tipoAtivo = new TipoAtivo(form.getNome(), form.getUsaCodChip(), form.getUsaImei(), form.getUsaMarca(),
				form.getUsaKeyLicenca(), form.getUsaMac(), form.getUsaNumChip(), form.getUsaProcessador(),
				form.getUsaRam(), form.getUsaCondicao(), form.getUsaArmazenamento(), form.getUsaModelo(),
				form.getUsaVinculo());

		return tipoAtivoRepository.save(tipoAtivo);
	}

	@Transactional
	public TipoAtivo atualizar(TipoAtivoForm form, Long idTipoAtivo) {
		TipoAtivo tipoAtivo = buscarTipoAtivoPorId(idTipoAtivo);

		tipoAtivo.setNome(form.getNome());
		tipoAtivo.setUsaCodChip(form.getUsaCodChip());
		tipoAtivo.setUsaImei(form.getUsaImei());
		tipoAtivo.setUsaMarca(form.getUsaMarca());
		tipoAtivo.setUsaKeyLicenca(form.getUsaKeyLicenca());
		tipoAtivo.setUsaMac(form.getUsaMac());
		tipoAtivo.setUsaNumChip(form.getUsaNumChip());
		tipoAtivo.setUsaProcessador(form.getUsaProcessador());
		tipoAtivo.setUsaRam(form.getUsaRam());
		tipoAtivo.setUsaCondicao(form.getUsaCondicao());
		tipoAtivo.setUsaArmazenamento(form.getUsaArmazenamento());
		tipoAtivo.setUsaModelo(form.getUsaModelo());
		tipoAtivo.setUsaVinculo(form.getUsaVinculo());

		return tipoAtivoRepository.save(tipoAtivo);
	}

	@Transactional
	public void excluir(Long idTipoAtivo) {
		TipoAtivo tipoAtivo = buscarTipoAtivoPorId(idTipoAtivo);
		tipoAtivoRepository.delete(tipoAtivo);
	}
}