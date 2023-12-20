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

import com.br.project.controller.dto.MenuDto;
import com.br.project.models.acesso.Menu;
import com.br.project.models.acesso.PerfilXMenuXSubmenu;
import com.br.project.models.acesso.Submenu;
import com.br.project.repository.MenuRepository;
import com.br.project.repository.PerfilXMenuRepository;
import com.br.project.repository.SubmenuRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private SubmenuRepository submenuRepository;

	@Autowired
	private PerfilXMenuRepository perfilMenuRepository;

	@GetMapping
	@Transactional
	public List<MenuDto> listar() {

		return menuRepository.findAll().stream().map(MenuDto::new).collect(Collectors.toList());
	}

	@PostMapping
	@Transactional
	public Menu cadastrarMenu(@RequestBody Menu form) {

		Menu menu = form;
		menuRepository.save(menu);

		return menu;

	}

	@PutMapping("/{id}")
	@Transactional
	public Menu atualizarMenu(@RequestBody Menu form, @PathVariable Long id) {

		Optional<Menu> menu = menuRepository.findById(id);

		if (menu.isEmpty()) {
			throw new EntityNotFoundException("Menu não encontrado!");
		}

		Menu menuAtualizado = menuRepository.getReferenceById(id);
		menuAtualizado.setIcone(form.getIcone());
		menuAtualizado.setTitulo(form.getTitulo());
		menuRepository.save(menuAtualizado);

		return menuAtualizado;

	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletarMenu(@PathVariable Long id) {

		Optional<Menu> menu = menuRepository.findById(id);
		List<PerfilXMenuXSubmenu> vinculo = perfilMenuRepository.findByIdMenu(menu.get());

		if (menu.isEmpty()) {
			throw new EntityNotFoundException("Menu Inexistente!");
		}

		if (!vinculo.isEmpty()) {
			StringBuilder mensagem = new StringBuilder("Menu está vinculado aos seguintes acessos: ");
			vinculo.forEach(v -> mensagem.append(v.getId()).append(", "));
			mensagem.append(" desvincule antes de apagar!");

			throw new DataIntegrityViolationException(mensagem.toString());
		}

		submenuRepository.deleteByIdMenu_Id(id);
		menuRepository.deleteById(id);
	}

	@PutMapping("/desvincular/{idMenu}/{idSubmenu}")
	@Transactional
	public Menu desvincularSubmenu(@PathVariable Long idMenu, @PathVariable Long idSubmenu) {

		Optional<Menu> menu = menuRepository.findById(idMenu);

		Optional<Submenu> submenu = submenuRepository.findById(idSubmenu);

		List<PerfilXMenuXSubmenu> vinculos = perfilMenuRepository.findByIdMenuAndIdSubmenu(menu.get(), submenu.get());

		if (menu.isEmpty()) {
			throw new EntityNotFoundException("Menu Inexistente!");
		}

		if (menu.isEmpty()) {
			throw new EntityNotFoundException("Submenu Inexistente!");
		}

		if (!menu.get().getSubmenu().contains(submenu.get())) {

			throw new EntityNotFoundException("Submenu não pertence ao menu informado! informe outro submenu.");
		}

		submenu.get().setIdMenu(null);
		submenuRepository.save(submenu.get());

		if (!vinculos.isEmpty()) {
			for (PerfilXMenuXSubmenu vinculo : vinculos) {
				vinculo.setIdSubmenu(null);
			}
		}

		return menu.get();
	}

}
