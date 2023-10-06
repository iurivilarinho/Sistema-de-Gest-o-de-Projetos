package com.br.project.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.project.controller.dto.EnvolvidosDto;
import com.br.project.controller.dto.PilarDto;
import com.br.project.models.Documento;
import com.br.project.models.Envolvidos;
import com.br.project.models.Pilar;
import com.br.project.models.Projeto;
import com.br.project.repository.DocumentoRepository;
import com.br.project.repository.EnvolvidosRepository;
import com.br.project.repository.PilarRepository;
import com.br.project.repository.ProjetoRepository;
import com.br.project.repository.TipoPilarRepository;
import com.br.project.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private EnvolvidosRepository envolvidosRepository;

	@Autowired
	private PilarRepository pilarRepository;

	@Autowired
	private TipoPilarRepository tipoPilarRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EntityManager entity;

	public void salvarDocumentos(List<MultipartFile> arquivos, Projeto projeto) {

		List<Documento> documentos = new ArrayList<>();

		arquivos.forEach((a) -> {
			try {

				documentos.add(new Documento(a.getName(), a.getContentType(), a.getBytes(), projeto));

			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		documentoRepository.saveAll(documentos);
		entity.flush();

	}

	public void salvarEnvolvidos(List<EnvolvidosDto> envolvidos, Projeto projeto) {

		envolvidosRepository.saveAll(envolvidos.stream()
				.map(e -> new Envolvidos(
						usuarioRepository.findById(e.getIdUsuario())
								.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!")),
						e.getPapel(), e.getCustoHora(), projeto))
				.collect(Collectors.toList()));

		entity.flush();

	}

	public void salvarPilares(List<PilarDto> pilares, Projeto projeto) {

		pilarRepository.saveAll(pilares.stream()
				.map(p -> new Pilar(p.getDescricao(), p.getSubPilar(), projeto, p.getValor(), p.getHoras(),
						tipoPilarRepository.findById(p.getIdTipo())
								.orElseThrow(() -> new EntityNotFoundException("Tipo de Pilar não encontrado!"))))
				.collect(Collectors.toList()));

		entity.flush();
	}

}
