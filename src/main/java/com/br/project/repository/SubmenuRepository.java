package com.br.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.project.models.acesso.Submenu;

public interface SubmenuRepository extends JpaRepository<Submenu, Long> {

	void deleteByIdMenu_Id(Long id);

}
