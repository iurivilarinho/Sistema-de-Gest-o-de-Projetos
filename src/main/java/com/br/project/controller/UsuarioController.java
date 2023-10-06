package com.br.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.form.UsuarioForm;
import com.br.project.models.Usuario;
import com.br.project.repository.UsuarioRepository;
import com.br.project.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@Transactional
	public ResponseEntity<Page<Usuario>> listar(Pageable paginacao) {
		return ResponseEntity.ok(usuarioRepository.findAll(paginacao));
	}

	@GetMapping("/{idUsuario}")
	public ResponseEntity<Usuario> listPorId(@PathVariable Long idUsuario) {

		return ResponseEntity.ok(usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado")));
	}

	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioForm form) {

		Usuario usuario = form.formulario();
		usuarioService.salvarPerfis(form.getPerfis(), usuario);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/validar/{token}")
	@SecurityRequirement(name = "") // Defina um nome vazio para remover o requisito de token
	public ResponseEntity<Usuario> porToken(@PathVariable String token) {

		return ResponseEntity.ok(usuarioService.getUsuarioPorToken(token));
	}
}
