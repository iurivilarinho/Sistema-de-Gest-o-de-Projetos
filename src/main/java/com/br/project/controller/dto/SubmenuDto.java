package com.br.project.controller.dto;

import com.br.project.models.acesso.Submenu;

public class SubmenuDto {

	private Long id;
	private Long idMenu;
	private String titulo;
	private String url;

	public SubmenuDto(Submenu submenu) {

		this.id = submenu.getId();
		this.idMenu = submenu.getIdMenu() != null ? submenu.getIdMenu().getId() : null;
		this.titulo = submenu.getTitulo();
		this.url = submenu.getUrl();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
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

}
