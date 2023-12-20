package com.br.project.controller.controleAcesso;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.controller.dto.SubmenuDto;
import com.br.project.form.SubmenuForm;
import com.br.project.models.acesso.Menu;
import com.br.project.models.acesso.Submenu;
import com.br.project.repository.MenuRepository;
import com.br.project.repository.SubmenuRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/submenu")
public class SubmenuController {

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private SubmenuRepository submenuRepository;

	@GetMapping
	@Transactional
	public List<SubmenuDto> listar() {
		return submenuRepository.findAll().stream().map(SubmenuDto::new).collect(Collectors.toList());
	}

	@PostMapping
	@Transactional
	public Submenu cadastrarSubmenu(@RequestBody SubmenuForm form) {

		if (form.getIdMenu() != null) {
			Optional<Menu> menu = menuRepository.findById(form.getIdMenu());

			if (menu.isEmpty()) {
				throw new EntityNotFoundException("Menu informado inexistente!");
			}
			Submenu submenu = form.cadastrar(menu.get());
			submenuRepository.save(submenu);
			return submenu;
		} else {

			Submenu submenu = form.cadastrar(null);
			submenuRepository.save(submenu);

			return submenu;
		}

	}

	@PutMapping("/{id}")
	@Transactional
	public Submenu atualizarSubmenu(@RequestBody SubmenuForm form, @PathVariable Long id) {

		Optional<Submenu> submenu = submenuRepository.findById(id);

		if (submenu.isEmpty()) {

			throw new EntityNotFoundException("Submenu informado inexistente!");
		}

		if (form.getIdMenu() != null) {
			Optional<Menu> menu = menuRepository.findById(form.getIdMenu());

			if (menu.isEmpty()) {
				throw new EntityNotFoundException("Menu informado inexistente!");
			}

			Submenu submenuAtualizado = form.atualizar(id, menu.get(), submenu.get());
			submenuRepository.save(submenuAtualizado);

			return submenuAtualizado;

		} else {

			Submenu submenuAtualizado = form.atualizar(id, null, submenu.get());
			submenuRepository.save(submenuAtualizado);

			return submenuAtualizado;
		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletarSubmenu(@PathVariable Long id) {

		Optional<Submenu> submenu = submenuRepository.findById(id);

		if (submenu.get().getIdMenu() != null) {
			throw new DataIntegrityViolationException("Submenu cadastrado ao menu"
					+ submenu.get().getIdMenu().getTitulo() + ", favor desvincular antes de apagar!");
		}

		if (submenu.isEmpty()) {

			throw new EntityNotFoundException("Submenu inexistente!");

		}

		submenuRepository.deleteById(id);
	}

}
