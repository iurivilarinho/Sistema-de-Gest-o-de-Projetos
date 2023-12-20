package com.br.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.project.models.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
