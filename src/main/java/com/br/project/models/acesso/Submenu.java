package com.br.project.models.acesso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbSubmenu")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Submenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonIgnore
	private Menu idMenu;

	private String titulo;

	private String url;

	public Submenu() {

	}

	public Submenu(Long id, String titulo, String url, Menu idMenu) {
		this.id = id;
		this.titulo = titulo;
		this.url = url;
		this.idMenu = idMenu;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Menu idMenu) {
		this.idMenu = idMenu;
	}

}
