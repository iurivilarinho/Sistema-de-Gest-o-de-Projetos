package com.br.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.form.TipoAtivoForm;
import com.br.project.models.TipoAtivo;
import com.br.project.service.TipoAtivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipo-ativo")
@Validated
public class TipoAtivoController {

	@Autowired
	private TipoAtivoService tipoAtivoService;

	@GetMapping
	public ResponseEntity<Page<TipoAtivo>> listar(Pageable paginacao) {
		return ResponseEntity.ok(tipoAtivoService.buscar(paginacao));
	}

	@GetMapping("/{idTipoAtivo}")
	public ResponseEntity<TipoAtivo> listarPorId(@PathVariable Long idTipoAtivo) {
		return ResponseEntity.ok(tipoAtivoService.buscarTipoAtivoPorId(idTipoAtivo));
	}

	@PostMapping
	public ResponseEntity<TipoAtivo> cadastrar(@RequestBody @Valid TipoAtivoForm form) {
		TipoAtivo tipoAtivo = tipoAtivoService.cadastrar(form);
		return ResponseEntity.ok(tipoAtivo);
	}

	@PutMapping("/{idTipoAtivo}")
	public ResponseEntity<TipoAtivo> atualizar(@PathVariable Long idTipoAtivo, @RequestBody @Valid TipoAtivoForm form) {
		TipoAtivo tipoAtivo = tipoAtivoService.atualizar(form, idTipoAtivo);
		return ResponseEntity.ok(tipoAtivo);
	}

	@DeleteMapping("/{idTipoAtivo}")
	public ResponseEntity<?> excluir(@PathVariable Long idTipoAtivo) {
		tipoAtivoService.excluir(idTipoAtivo);
		return ResponseEntity.ok().build();
	}

}
