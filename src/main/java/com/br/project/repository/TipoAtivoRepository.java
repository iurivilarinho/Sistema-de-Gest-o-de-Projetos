package com.br.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.project.models.TipoAtivo;

@Repository
public interface TipoAtivoRepository extends JpaRepository<TipoAtivo, Long> {

}
