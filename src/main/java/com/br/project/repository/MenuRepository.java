package com.br.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.project.models.acesso.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
