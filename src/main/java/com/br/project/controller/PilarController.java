package com.br.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.models.Pilar;
import com.br.project.repository.PilarRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pilar")
@SecurityRequirement(name = "bearer-key")
public class PilarController {
	
	@Autowired
	private PilarRepository pilaroRepository;

	@GetMapping
	@Transactional
	public ResponseEntity<Page<Pilar>> listar(Pageable paginacao){
		return ResponseEntity.ok(pilaroRepository.findAll(paginacao)) ;
	}
}
