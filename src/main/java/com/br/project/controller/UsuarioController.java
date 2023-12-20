package com.br.project.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.project.controller.dto.UsuarioDto;
import com.br.project.form.UsuarioForm;
import com.br.project.models.Usuario;
import com.br.project.models.acesso.Perfil;
import com.br.project.models.acesso.UsuarioPerfil;
import com.br.project.repository.PerfilRepository;
import com.br.project.repository.UsuarioPerfilRepository;
import com.br.project.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository;

	@GetMapping
	@Cacheable(value = "usuarioCache")
	public Page<UsuarioDto> lista(@RequestParam(required = false) Boolean vinculados,
			@RequestParam(required = false) String search, Pageable paginacao) {

		return usuarioService.buscar(vinculados, search, paginacao);
	}

	@GetMapping("/validar/{token}")
	@Cacheable(value = "usuarioCache")
	public UsuarioDto lista(@PathVariable String token) {

		return new UsuarioDto(usuarioService.getUsuarioPorToken(token));
	}

	@PostMapping(consumes = { "multipart/form-data" })
	@CacheEvict(value = "usuarioCache", allEntries = true)
	public ResponseEntity<UsuarioDto> cadastrar(@RequestPart("img") MultipartFile img,
			@RequestPart("form") @Valid UsuarioForm form) throws IOException {

		Usuario usuario = usuarioService.cadastrar(form, img);

		return ResponseEntity.ok(new UsuarioDto(usuario));
	}

	@GetMapping("/{id}")
	@Cacheable(value = "usuarioCache")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id) {

		Usuario usuario = usuarioService.buscarUsuarioPorId(id);

		return ResponseEntity.ok(new UsuarioDto(usuario));

	}

	@PutMapping(value = "/atualizar/{id}", consumes = { "multipart/form-data" })
	@CacheEvict(value = "usuarioCache", allEntries = true)
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestPart("img") MultipartFile img,
			@RequestPart("form") @Valid UsuarioForm form) throws IOException {

		Usuario usuario = usuarioService.atualizar(id, form, img);

		return ResponseEntity.ok(new UsuarioDto(usuario));

	}

	@PutMapping("/desativar/{id}")
	@CacheEvict(value = "usuarioCache", allEntries = true)
	public ResponseEntity<?> desativar(@PathVariable Long id) {

		usuarioService.ativarDesativar(id, false);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/perfil/{idUsuario}/{idPerfil}")
	@Transactional
	@CacheEvict(value = "usuarioCache", allEntries = true)
	public Usuario vincularPerfil(@PathVariable Long idUsuario, @PathVariable Long idPerfil) {

		Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
		Optional<Perfil> perfil = perfilRepository.findById(idPerfil);

		if (usuario.getPerfis().contains(perfil.get())) {
			throw new DataIntegrityViolationException("Perfil ja associado ao usuário!");
		}

		if (perfil.isEmpty()) {
			throw new EntityNotFoundException("Perfil não encontrado");
		}

		UsuarioPerfil vinculo = new UsuarioPerfil(idUsuario, idPerfil);

		usuarioPerfilRepository.save(vinculo);

		return usuario;

	}

	@DeleteMapping("/perfil/{idUsuario}/{idPerfil}")
	@Transactional
	@CacheEvict(value = "usuarioCache", allEntries = true)
	public void deletarVinculo(@PathVariable Long idUsuario, @PathVariable Long idPerfil) {

		Optional<UsuarioPerfil> vinculo = usuarioPerfilRepository.findByIdUsuarioAndIdPerfil(idUsuario, idPerfil);
		if (vinculo.isPresent()) {
			usuarioPerfilRepository.deleteByIdUsuarioAndIdPerfil(idUsuario, idPerfil);
		} else {

			throw new EntityNotFoundException("Vinculo inexistente!!");

		}
	}
}
