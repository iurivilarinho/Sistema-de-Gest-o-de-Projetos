package com.br.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.project.models.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{

}
