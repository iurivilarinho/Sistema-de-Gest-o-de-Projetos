package com.br.project.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.br.project.controller.dto.DocumentoDto;
import com.br.project.filtros.ProjetoFiltros;
import com.br.project.form.ProjetoForm;
import com.br.project.models.Pilar;
import com.br.project.models.Projeto;
import com.br.project.models.enums.Status;
import com.br.project.repository.ProjetoRepository;
import com.br.project.util.PilarUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjetoService {

	@Autowired
	private EnvolvidosService envolvidosService;

	@Autowired
	private PilarService pilarService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private ProjetoFiltros filtros;

	@Autowired
	private PilarUtil util;

	@Transactional(readOnly = true)
	public Page<Projeto> buscar(String nome, LocalDate dataIni, LocalDate dataFim, Long idUsuario, List<Status> status,
			Status statusNot, Pageable paginacao) {
		return filtros.filtro(nome, dataIni, dataFim, idUsuario, status, statusNot, paginacao);
	}

	@Transactional(readOnly = true)
	public Projeto buscarProjetoPorId(Long id) {
		return projetoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado para o ID: " + id));
	}

	@Transactional
	public Projeto cadastrar(ProjetoForm form, List<MultipartFile> arquivos) throws IOException {
		Projeto projeto = new Projeto(form.getNome(), Status.PENDENTE_APROVACAO, form.getJustificativa(),
				usuarioService.usuarioLogado(), form.getDescricao(),
				documentoService.converterEmListaDocumento(arquivos), form.getLinkDocs());
		projetoRepository.save(projeto);
		envolvidosService.salvarEnvolvidos(form.getEnvolvidos(), projeto);
		pilarService.salvarPilares(form.getPilares(), projeto);
		calcularPontos(projeto);
		return projeto;
	}

	@Transactional
	public Projeto atualizar(ProjetoForm form, List<MultipartFile> arquivos, Long id) throws IOException {
		Projeto projeto = buscarProjetoPorId(id);
		projeto.setNome(form.getNome());
		projeto.setJustificativa(form.getJustificativa());
		projeto.setDescricao(form.getDescricao());
		projeto.setSprint(form.getSprint());
		projeto.setDataPrevista(form.getDataPrevista());
		projeto.setDataLevantamento(form.getDataLevantamento());
		projeto.setLinkDocs(form.getLinkDocs());

		projeto.getDocumentos().removeIf(doc -> !form.getDocumentos().contains(new DocumentoDto(doc)));
		if (arquivos != null) {
			projeto.getDocumentos().addAll(documentoService.converterEmListaDocumento(arquivos));

		}
		projetoRepository.save(projeto);
		// envolvidosService.apagar(id);
		// envolvidosService.salvarEnvolvidos(form.getEnvolvidos(), projeto);
		// pilarService.apagar(id);
		if (projeto.getPilares() != null) {
			projeto.getPilares().clear();
			projeto.getPilares().addAll(form.getPilares().stream().map(
					p -> new Pilar(p.getDescricao(), p.getSubPilar(), projeto, p.getValor(), p.getHoras(), p.getTipo()))
					.collect(Collectors.toList()));
			// pilarService.salvarPilares(form.getPilares(), projeto);
		}

		calcularPontos(projeto);
		return projeto;
	}

	@Transactional
	public void alterarStatus(Status status, Long id) {
		Projeto projeto = buscarProjetoPorId(id);
		projeto.setSituation(status);
	}

	@Transactional
	public void calcularPontos(Projeto projeto) {
		AtomicInteger pontos = new AtomicInteger(0);
		projeto.getPilares().forEach(p -> {

			if (p.getTipo().getTipo().equals("SUSTENTABILIDADE")) {
				pontos.addAndGet(util.calculaPontosSubpilar(p.getSubPilar()));
			}
			if (p.getTipo().getTipo().equals("PROCESSO_PRODUTIVIDADE")) {
				if (p.getHoras() != null) {
					pontos.addAndGet(util.calculaPontosMetricaHoras(p.getHoras()));
				}
			}
			if (p.getTipo().getTipo().equals("RETORNO_FINANCEIRO")) {
				if (p.getValor() != null) {
					pontos.addAndGet(util.calculaPontosValor(p.getValor()));
				}
			}
			if (p.getTipo().getTipo().equals("EXCELENCIA_INOVACAO")) {
				pontos.addAndGet(util.calculaPontosSubpilar(p.getSubPilar()));
			}
			if (p.getTipo().getTipo().equals("PARTICIPACAO_MERCADO")) {
				pontos.addAndGet(2);
			}
			if (p.getTipo().getTipo().equals("SATISFACAO_CLIENTE")) {
				pontos.addAndGet(util.calculaPontosSubpilar(p.getSubPilar()));
			}
		});
		projeto.setPontos(pontos.get());
		projetoRepository.save(projeto);
	}

}
