package com.br.project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.br.project.form.AtivoForm;
import com.br.project.models.Ativo;
import com.br.project.service.AtivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ativo")
public class AtivoController {

	@Autowired
	private AtivoService ativoService;

	@GetMapping
	public ResponseEntity<Page<Ativo>> listar(@RequestParam(required = false) String tag,
			@RequestParam(required = false) Boolean status, @RequestParam(required = false) String search,
			@RequestParam(required = false) Long idUsuario, @RequestParam(required = false) Long idTipo,
			@RequestParam(required = false) Boolean vinculado, Pageable paginacao) {
		return ResponseEntity.ok(ativoService.buscar(search, status, search, idUsuario, idTipo, vinculado, paginacao));
	}

	@GetMapping("/{idAtivo}")
	public ResponseEntity<Ativo> listarPorId(@PathVariable Long idAtivo) {
		return ResponseEntity.ok(ativoService.buscarAtivoPorId(idAtivo));
	}

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<Ativo> cadastrar(@RequestPart("form") @Valid AtivoForm form,
			@RequestPart(value = "imagens", required = false) List<MultipartFile> imagens,
			@RequestPart(value = "notaFiscal", required = false) MultipartFile notaFiscal) throws IOException {

		Ativo ativo = ativoService.cadastrar(form, imagens, notaFiscal);

		return ResponseEntity.ok(ativo);
	}

	@PutMapping(value = "/{idAtivo}", consumes = { "multipart/form-data" })
	public ResponseEntity<Ativo> atualizar(@RequestPart("form") @Valid AtivoForm form,
			@RequestPart(value = "imagens", required = false) List<MultipartFile> imagens,
			@RequestPart(value = "notaFiscal", required = false) MultipartFile notaFiscal, @PathVariable Long idAtivo)
			throws IOException {
		Ativo ativo = ativoService.atualizar(form, idAtivo, notaFiscal, imagens);

		return ResponseEntity.ok(ativo);
	}

	@PutMapping("vincular/{idUsuario}/{idAtivo}")
	public ResponseEntity<?> vincular(@PathVariable Long idUsuario, @PathVariable Long idAtivo) {
		ativoService.vincularUsuario(idUsuario, idAtivo);
		return ResponseEntity.ok().build();
	}

	@PutMapping("desvincular/{idUsuario}/{idAtivo}")
	public ResponseEntity<?> desvincular(@PathVariable Long idUsuario, @PathVariable Long idAtivo) {
		ativoService.desvincularUsuario(idUsuario, idAtivo);
		return ResponseEntity.ok().build();
	}

	@PutMapping("situacao/{status}/{idAtivo}")
	public ResponseEntity<?> ativarDesativar(@PathVariable Boolean status, @PathVariable Long idAtivo) {
		ativoService.ativarDesativar(status, idAtivo);
		return ResponseEntity.ok().build();
	}
}
