package com.br.project.models.acesso;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbPerfil_Menu_Usuario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PerfilXMenuXSubmenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idPerfil;
	@ManyToOne
	private Menu idMenu;
	@ManyToOne
	private Submenu idSubmenu;

	public PerfilXMenuXSubmenu() {

	}

	public PerfilXMenuXSubmenu(Long idPerfil, Menu idMenu, Submenu idSubmenu) {

		this.idPerfil = idPerfil;
		this.idMenu = idMenu;
		this.idSubmenu = idSubmenu;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Menu getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Menu idMenu) {
		this.idMenu = idMenu;
	}

	public Submenu getIdSubmenu() {
		return idSubmenu;
	}

	public void setIdSubmenu(Submenu idSubmenu) {
		this.idSubmenu = idSubmenu;
	}

}
