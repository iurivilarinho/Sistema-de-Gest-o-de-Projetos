package com.br.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.project.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByLogin(String username);

}
