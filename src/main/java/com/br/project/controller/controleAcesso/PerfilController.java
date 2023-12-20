package com.br.project.controller.controleAcesso;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.models.acesso.Perfil;
import com.br.project.repository.PerfilRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	PerfilRepository perfilRepository;

	@GetMapping
	@Transactional
	public List<Perfil> listarPerfil() {

		return perfilRepository.findAll();

	}

	@GetMapping("/{idPerfil}")
	@Transactional
	public Perfil listarPerfil(@PathVariable Long idPerfil) {
		return perfilRepository.findById(idPerfil)
				.orElseThrow(() -> new EntityNotFoundException("Etiqueta não encontrada para o ID: " + idPerfil));

	}

	@PostMapping
	@Transactional
	public Perfil cadastrar(@RequestBody Perfil perfil) {

		perfilRepository.save(perfil);

		return perfil;

	}

	@PutMapping("/{idPerfil}")
	@Transactional
	public Perfil AtualizarPerfil(@PathVariable Long idPerfil, @RequestBody Perfil perfil) {

		Optional<Perfil> optional = perfilRepository.findById(idPerfil);
		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Perfil Não Encontrado");
		}

		optional.get().atualizaNome(perfil.getNome());

		return optional.get();

	}

	@DeleteMapping("/{idPerfil}")
	@Transactional
	public void deletarPerfil(@PathVariable Long idPerfil) {
		Optional<Perfil> perfil = perfilRepository.findById(idPerfil);
		if (perfil.isEmpty()) {
			throw new EntityNotFoundException("Perfil Não Encontrado");
		}

		perfilRepository.deleteById(idPerfil);
	}
}
