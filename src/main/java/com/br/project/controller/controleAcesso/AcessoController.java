package com.br.project.controller.controleAcesso;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.models.acesso.Menu;
import com.br.project.models.acesso.Perfil;
import com.br.project.models.acesso.PerfilXMenuXSubmenu;
import com.br.project.models.acesso.Submenu;
import com.br.project.repository.MenuRepository;
import com.br.project.repository.PerfilRepository;
import com.br.project.repository.PerfilXMenuRepository;
import com.br.project.repository.SubmenuRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/acesso")
public class AcessoController {

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private SubmenuRepository submenuRepository;

	@Autowired
	private PerfilXMenuRepository perfilMenuRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@GetMapping
	@Transactional
	public List<PerfilXMenuXSubmenu> listarAcesso() {

		return perfilMenuRepository.findAll();

	}

	@GetMapping("/{idPerfil}")
	@Transactional
	public List<PerfilXMenuXSubmenu> listarAcessoPorIdPerfil(@PathVariable Long idPerfil) {

		List<PerfilXMenuXSubmenu> acessos = perfilMenuRepository.findByIdPerfil(idPerfil);

		if (acessos.isEmpty()) {
			throw new EntityNotFoundException("Acesso inexistente para Perfil informado");
		}

		return acessos;

	}

	@PostMapping
	@Transactional
	public PerfilXMenuXSubmenu liberarAcesso(
	    @RequestParam Long idPerfil,
	    @RequestParam Long idMenu,
	    @RequestParam(required = false) Long idSubmenu) {

	    Menu menu = menuRepository.findById(idMenu)
	        .orElseThrow(() -> new EntityNotFoundException("Menu não encontrado!"));
	    @SuppressWarnings("unused")
		Perfil perfil = perfilRepository.findById(idPerfil)
	        .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado!"));

	    if (idSubmenu != null) {
	        Submenu submenu = submenuRepository.findById(idSubmenu)
	            .orElseThrow(() -> new EntityNotFoundException("Submenu não encontrado!"));

	        if (!menu.getSubmenu().contains(submenu)) {
	            throw new EntityNotFoundException("Submenu não pertence ao menu selecionado!");
	        }

	        PerfilXMenuXSubmenu perfilmenu = new PerfilXMenuXSubmenu(idPerfil, menu, submenu);
	        perfilMenuRepository.save(perfilmenu);
	        return perfilmenu;
	    }

	    PerfilXMenuXSubmenu perfilmenu = new PerfilXMenuXSubmenu(idPerfil, menu, null);
	    perfilMenuRepository.save(perfilmenu);
	    return perfilmenu;
	}


	@DeleteMapping
	@Transactional
	public void deletarAcesso(@RequestParam Long idPerfil, @RequestParam Long idMenu,
			@RequestParam(required = false) Long idSubmenu) {

		Optional<PerfilXMenuXSubmenu> acesso = perfilMenuRepository.findByIdPerfilAndIdMenu_Id(idPerfil, idMenu);

//		if (idSubmenu != null) {
//			perfilMenuRepository.deleteByIdPerfilAndIdMenuAndIdSubmenu(idPerfil, idMenu, idSubmenu);
//		}

		if (!acesso.isPresent()) {
			throw new EntityNotFoundException("Acesso Inexistente");
		}

		if (idSubmenu != null && acesso.get().getIdSubmenu() == null) {
			throw new EntityNotFoundException("Necessário informar o Submenu para deleção!");
		}

		//perfilMenuRepository.deleteByIdPerfilAndIdMenu(idPerfil, idMenu);
	}

}
