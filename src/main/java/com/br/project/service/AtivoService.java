package com.br.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.br.project.controller.dto.DocumentoDto;
import com.br.project.filtros.AtivoFiltros;
import com.br.project.form.AtivoForm;
import com.br.project.models.Ativo;
import com.br.project.models.Movimentacoes;
import com.br.project.models.Usuario;
import com.br.project.models.enums.TipoMovimentacao;
import com.br.project.repository.AtivoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AtivoService {

	@Autowired
	private AtivoRepository ativoRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private TipoAtivoService tipoAtivoService;

	@Autowired
	private AtivoFiltros ativoFiltros;

	@Transactional(readOnly = true)
	public Ativo buscarAtivoPorId(Long idAtivo) {
		return ativoRepository.findById(idAtivo)
				.orElseThrow(() -> new EntityNotFoundException("Ativo não encontrado para o ID: " + idAtivo));
	}

	@Transactional(readOnly = true)
	public void buscarAtivoPorTag(String tag) {
		ativoRepository.findByTag(tag).ifPresent(ativo -> {
			throw new DataIntegrityViolationException("Já existe Ativo cadastrado para a TAG: " + tag);
		});
	}

	@Transactional(readOnly = true)
	public Page<Ativo> buscar(String tag, Boolean status, String search, Long idUsuario, Long idTipo, Boolean vinculado,
			Pageable paginacao) {
		return ativoFiltros.filtro(search, status, search, idUsuario, idTipo, vinculado, paginacao);
	}

	@Transactional
	public Ativo cadastrar(AtivoForm form, List<MultipartFile> imagens, MultipartFile notaFiscal) throws IOException {
		buscarAtivoPorTag(form.getTag());
		Ativo ativo = new Ativo(form.getTag(), form.getCodChip(), form.getImei(), form.getMarca(), form.getKeyLicenca(),
				form.getMac(), form.getNumChip(), form.getProcessador(), form.getRam(), form.getCondicao(),
				form.getObservacao(), form.getModelo(), form.getArmazenamento(),
				form.getIdUsuarioVinculado() != null ? usuarioService.buscarUsuarioPorId(form.getIdUsuarioVinculado())
						: null,
				tipoAtivoService.buscarTipoAtivoPorId(form.getIdTipo()),
				notaFiscal != null ? documentoService.converterEmDocumento(notaFiscal) : null);
		ativo.getMovimentacoes().add(new Movimentacoes(ativo, usuarioService.usuarioLogado(), TipoMovimentacao.CADASTRO,
				"Ativo Cadastrado com sucesso por " + usuarioService.usuarioLogado().getNome() + "."));
		if (imagens != null) {
			ativo.getImagens().addAll(documentoService.converterEmListaDocumento(imagens));
		}
		ativoRepository.save(ativo);
		return ativo;
	}

	@Transactional
	public void vincularUsuario(Long idUsuario, Long idAtivo) {
		Ativo ativo = buscarAtivoPorId(idAtivo);
		if (ativo.getUsuarioVinculado() != null) {
			throw new DataIntegrityViolationException("Ativo para a TAG: " + ativo.getTag()
					+ " já esta vinculado a um usuario! Desvincule antes de continuar.");
		} else {
			Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
			ativo.setUsuarioVinculado(usuario);
//			ativo.getMovimentacoes().add(new Movimentacoes(ativo, usuario, TipoMovimentacao.ENTREGA,
//					"Ativo entregue com sucesso ao " + usuario.getNome()));
		}
	}

	@Transactional
	public void desvincularUsuario(Long idUsuario, Long idAtivo) {
		Ativo ativo = buscarAtivoPorId(idAtivo);

		if (ativo.getUsuarioVinculado() == null) {
			throw new DataIntegrityViolationException(
					"Ativo para a TAG: " + ativo.getTag() + " não possui nenhum vínculo a ser desvinculado!");
		}

		if (!ativo.getUsuarioVinculado().equals(usuarioService.buscarUsuarioPorId(idUsuario))) {
			throw new DataIntegrityViolationException(
					"O usuário informado não pertence ao ativo selecionado. Verifique e tente novamente.");
		}

		Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
		ativo.setUsuarioVinculado(null);
		ativo.getMovimentacoes().add(new Movimentacoes(ativo, usuario, TipoMovimentacao.DEVOLUCAO,
				"Ativo vinculado ao" + usuario.getNome() + " foi devolvido com sucesso. "));
	}

	@Transactional
	public Ativo atualizar(AtivoForm form, Long idAtivo, MultipartFile notaFiscal, List<MultipartFile> imagens)
			throws IOException {
		Ativo ativo = buscarAtivoPorId(idAtivo);

		ativo.setTag(form.getTag());
		ativo.setCodChip(form.getCodChip());
		ativo.setImei(form.getImei());
		ativo.setMarca(form.getMarca());
		ativo.setKeyLicenca(form.getKeyLicenca());
		ativo.setMac(form.getMac());
		ativo.setNumChip(form.getNumChip());
		ativo.setProcessador(form.getProcessador());
		ativo.setRam(form.getRam());
		ativo.setCondicao(form.getCondicao());
		ativo.setObservacao(form.getObservacao());
		ativo.setModelo(form.getModelo());
		ativo.setArmazenamento(form.getArmazenamento());
		ativo.setTipo(tipoAtivoService.buscarTipoAtivoPorId(form.getIdTipo()));
		ativo.getMovimentacoes().add(new Movimentacoes(ativo, usuarioService.usuarioLogado(), TipoMovimentacao.ENTREGA,
				"Ativo atualizado com sucesso por " + usuarioService.usuarioLogado().getNome() + "."));
		ativo.setNotaFiscal(
				notaFiscal != null ? documentoService.converterEmDocumento(notaFiscal) : null);

		ativo.getImagens().removeIf(doc -> !form.getImagens().contains(new DocumentoDto(doc)));
		if (imagens != null) {
			ativo.getImagens().addAll(documentoService.converterEmListaDocumento(imagens));

		}
		ativoRepository.save(ativo);
		return ativo;
	}

	@Transactional
	public void ativarDesativar(Boolean status, Long idAtivo) {
		Ativo ativo = buscarAtivoPorId(idAtivo);
		ativo.setStatus(status);
		ativo.getMovimentacoes().add(new Movimentacoes(ativo, usuarioService.usuarioLogado(),
				status.equals(true) ? TipoMovimentacao.ATIVACAO : TipoMovimentacao.DESATIVACAO,
				status.equals(true) ? "Ativo ativado com sucesso por " + usuarioService.usuarioLogado().getNome() + "."
						: "Ativo desativado com sucesso por " + usuarioService.usuarioLogado().getNome() + "."));
	}
}
