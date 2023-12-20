package com.br.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.project.models.acesso.Menu;
import com.br.project.models.acesso.PerfilXMenuXSubmenu;
import com.br.project.models.acesso.Submenu;

@Repository
public interface PerfilXMenuRepository extends JpaRepository<PerfilXMenuXSubmenu, Long> {

	Optional<PerfilXMenuXSubmenu> findByIdPerfilAndIdMenu_IdAndIdSubmenu_Id(Long idPerfil, Long idMenu, Long idSubmenu);

	void deleteByIdPerfilAndIdMenu_IdAndIdSubmenu_Id(Long idPerfil, Long idMenu, Long idSubmenu);

	Optional<PerfilXMenuXSubmenu> findByIdPerfilAndIdMenu_Id(Long idPerfil, Long idMenu);

	void deleteByIdPerfilAndIdMenu_Id(Long idPerfil, Long idMenu);

	List<PerfilXMenuXSubmenu> findByIdMenuAndIdSubmenu(Menu menu, Submenu submenu);

	List<PerfilXMenuXSubmenu> findByIdMenu(Menu menu);

	List<PerfilXMenuXSubmenu> findByIdPerfil(Long idPerfil);

}
