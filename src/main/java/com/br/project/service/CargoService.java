package com.br.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.project.form.CargoForm;
import com.br.project.models.Cargo;
import com.br.project.repository.CargoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	@Transactional(readOnly = true)
	public Cargo buscarCargoPorId(Long idCargo) {
		return cargoRepository.findById(idCargo)
				.orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado para o ID: " + idCargo));
	}

	@Transactional(readOnly = true)
	public Page<Cargo> buscar(Pageable paginacao) {
		return cargoRepository.findAll(paginacao);
	}

	@Transactional
	public Cargo cadastrar(CargoForm form) {
		Cargo cargo = new Cargo(form.getNome(), form.getDescricao(), form.getDepartamento());

		return cargoRepository.save(cargo);
	}

	@Transactional
	public Cargo atualizar(CargoForm form, Long idCargo) {
		Cargo cargo = buscarCargoPorId(idCargo);

		cargo.setNome(form.getNome());
		cargo.setDescricao(form.getDescricao());
		cargo.setDepartamento(form.getDepartamento());

		return cargoRepository.save(cargo);
	}

	@Transactional
	public void ativarDesaivar(Long idCargo, Boolean status) {
		Cargo cargo = buscarCargoPorId(idCargo);
		cargo.setStatus(status);
		cargoRepository.save(cargo);
	}
}
