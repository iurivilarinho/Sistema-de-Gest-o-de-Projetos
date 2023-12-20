package com.br.project.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.br.project.models.acesso.Menu;
import com.br.project.models.acesso.Submenu;

public class MenuDto {

	private Long id;
	private String titulo;
	private String icone;
	private List<SubmenuDto> submenu = new ArrayList<>();

	public MenuDto(Menu menu) {

		this.id = menu.getId();
		this.titulo = menu.getTitulo();
		this.icone = menu.getIcone();

		List<Submenu> submenus = menu.getSubmenu();
		submenus.forEach(f -> this.submenu.add(new SubmenuDto(f)));

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

	public List<SubmenuDto> getSubmenu() {
		return submenu;
	}

	public void setSubmenu(List<SubmenuDto> submenu) {
		this.submenu = submenu;
	}

}
