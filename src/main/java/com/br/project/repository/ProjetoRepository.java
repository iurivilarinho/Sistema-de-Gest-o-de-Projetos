package com.br.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.project.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{

}