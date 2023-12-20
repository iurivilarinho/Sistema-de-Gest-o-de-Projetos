package com.br.project.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.project.form.ProjetoForm;
import com.br.project.models.Projeto;
import com.br.project.models.enums.Status;
import com.br.project.service.ProjetoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/projeto")
@SecurityRequirement(name = "bearer-key")
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;

	@GetMapping
	public ResponseEntity<Page<Projeto>> listar(@RequestParam(required = false) String nome,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataIni,
			@DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(required = false) LocalDate dataFim,
			@RequestParam(required = false) Long idUsuario, @RequestParam(required = false) List<Status> status,
			@RequestParam(required = false) Status statusNot,
			@PageableDefault(sort = "pontos", direction = Direction.DESC) Pageable paginacao) {

		return ResponseEntity
				.ok(projetoService.buscar(nome, dataIni, dataFim, idUsuario, status, statusNot, paginacao));
	}

	@GetMapping("/{idProjeto}")
	public ResponseEntity<Projeto> listarPorId(@PathVariable Long idProjeto) {
		return ResponseEntity.ok(projetoService.buscarProjetoPorId(idProjeto));

	}

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<Projeto> cadastrar(@RequestPart("form") ProjetoForm form,
			@RequestPart(value = "documentos", required = false) List<MultipartFile> documentos) throws IOException {

		Projeto projeto = projetoService.cadastrar(form, documentos);

		return ResponseEntity.ok(projeto);

	}

	@PutMapping(value = "/{idProjeto}", consumes = { "multipart/form-data" })
	public ResponseEntity<Projeto> cadastrar(@RequestPart("form") ProjetoForm form,
			@RequestPart(value = "documentos", required = false) List<MultipartFile> documentos,
			@PathVariable Long idProjeto) throws IOException {

		Projeto projeto = projetoService.atualizar(form, documentos, idProjeto);

		return ResponseEntity.ok(projeto);

	}

	@PutMapping("/cancelar/{idProjeto}")
	public ResponseEntity<?> cancelar(@PathVariable Long idProjeto) throws IOException {

		projetoService.alterarStatus(Status.CANCELADO, idProjeto);

		return ResponseEntity.ok().build();

	}

	@PutMapping("/movimentar/{idProjeto}")
	public ResponseEntity<?> movimentar(@PathVariable Long idProjeto, @RequestParam String status) throws IOException {
		Status novoStatus = Status.valueOf(status.toUpperCase());

		projetoService.alterarStatus(novoStatus, idProjeto);

		return ResponseEntity.ok().build();
	}

}
