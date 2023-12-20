package com.br.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.form.CargoForm;
import com.br.project.models.Cargo;
import com.br.project.service.CargoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@GetMapping
	@Cacheable(value = "cargoCache")
	public ResponseEntity<Page<Cargo>> lista(Pageable paginacao) {
		return ResponseEntity.ok(cargoService.buscar(paginacao));
	}

	@PostMapping
	@CacheEvict(value = "cargoCache", allEntries = true)
	public ResponseEntity<Cargo> cadastrar(@Valid CargoForm form) {
		Cargo cargo = cargoService.cadastrar(form);
		return ResponseEntity.ok(cargo);
	}

	@GetMapping("/{id}")
	@Cacheable(value = "cargoCache")
	public ResponseEntity<Cargo> detalhar(@PathVariable Long id) {
		Cargo cargo = cargoService.buscarCargoPorId(id);
		return ResponseEntity.ok(cargo);
	}

	@PutMapping("/{id}")
	@CacheEvict(value = "cargoCache", allEntries = true)
	public ResponseEntity<Cargo> atualizar(@PathVariable Long id, @Valid CargoForm form) {
		Cargo cargo = cargoService.atualizar(form, id);
		return ResponseEntity.ok(cargo);
	}

	@PutMapping("/status/{id}")
	@CacheEvict(value = "cargoCache", allEntries = true)
	public ResponseEntity<?> excluir(@PathVariable Long id, @PathVariable Boolean status) {
		cargoService.ativarDesaivar(id, status);
		return ResponseEntity.ok().build();
	}
}
