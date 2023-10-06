package com.br.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.project.models.acesso.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
