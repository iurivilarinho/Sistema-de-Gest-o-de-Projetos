package com.br.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.project.models.Pilar;

@Repository
public interface PilarRepository extends JpaRepository<Pilar, Long> {

	void deleteByProjeto_Id(Long idProjeto);

	Optional<Pilar> findByProjeto_Id(Long idProjeto);

}
