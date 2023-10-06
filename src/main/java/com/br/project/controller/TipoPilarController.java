package com.br.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.models.TipoPilar;
import com.br.project.repository.TipoPilarRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/tipo_pilar")
@SecurityRequirement(name = "bearer-key")
public class TipoPilarController {

	@Autowired
	private TipoPilarRepository tipoPilarRepository;

	@GetMapping
	public ResponseEntity<List<TipoPilar>> listar() {

		return ResponseEntity.ok(tipoPilarRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<TipoPilar> cadastrar(@RequestBody TipoPilar tipo) {

		tipoPilarRepository.save(tipo);
		return ResponseEntity.ok(tipo);
	}

}
