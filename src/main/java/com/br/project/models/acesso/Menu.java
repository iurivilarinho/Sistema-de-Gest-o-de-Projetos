package com.br.project.models.acesso;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbMenu")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String icone;

	@OneToMany(mappedBy = "idMenu")
	private List<Submenu> submenu = new ArrayList<>();

	public Menu() {

	}

	public Menu(Long id, String titulo, String icone, List<Submenu> submenu) {
		this.id = id;
		this.titulo = titulo;
		this.icone = icone;
		this.submenu = submenu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public List<Submenu> getSubmenu() {
		return submenu;
	}

	public void setSubmenu(List<Submenu> submenu) {
		this.submenu = submenu;
	}

}
