package com.br.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.project.models.Ativo;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long>, JpaSpecificationExecutor<Ativo> {

	Optional<Ativo> findByTag(String tag);

}
