package com.br.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.project.models.Envolvidos;

@Repository
public interface EnvolvidosRepository extends JpaRepository<Envolvidos, Long> {

	void deleteByProjeto_Id(Long idProjeto);

	Optional<Envolvidos> findByProjeto_Id(Long idProjeto);

}
