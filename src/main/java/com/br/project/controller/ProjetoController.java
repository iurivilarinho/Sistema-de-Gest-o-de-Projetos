package com.br.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.project.form.ProjetoForm;
import com.br.project.models.Projeto;
import com.br.project.repository.ProjetoRepository;
import com.br.project.service.ProjetoService;
import com.br.project.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/projeto")
@SecurityRequirement(name = "bearer-key")
public class ProjetoController {

	@Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@Transactional
	public ResponseEntity<Page<Projeto>> listar(Pageable paginacao) {

		return ResponseEntity.ok(projetoRepository.findAll(paginacao));
	}

	@GetMapping("/{idProjeto}")
	@Transactional
	public ResponseEntity<Projeto> listarPorId(@PathVariable Long idProjeto) {

		return ResponseEntity.ok(projetoRepository.findById(idProjeto)
				.orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado!")));

	}

	@PostMapping(consumes = { "multipart/form-data" })
	@Transactional
	public ResponseEntity<Projeto> cadastrar(@RequestPart("form") ProjetoForm form,
			@RequestPart("documentos") List<MultipartFile> documentos) {

		Projeto projeto = form.cadastrar(usuarioService.usuarioLogado());
		projetoRepository.save(projeto);
		projetoService.salvarDocumentos(documentos, projeto);
		projetoService.salvarEnvolvidos(form.getEnvolvidos(), projeto);
		projetoService.salvarPilares(form.getPilares(), projeto);

		return ResponseEntity.ok(projeto);

	}

}
