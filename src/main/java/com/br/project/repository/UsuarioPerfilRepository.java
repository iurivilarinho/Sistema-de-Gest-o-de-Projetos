package com.br.project.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.project.models.acesso.UsuarioPerfil;


public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {

	Optional<UsuarioPerfil> findByIdUsuarioAndIdPerfil(Long idUsuario, Long idPerfil);

	void deleteByIdUsuarioAndIdPerfil(Long idUsuario, Long idPerfil);

}
