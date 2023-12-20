package com.br.project.form;

import com.br.project.models.acesso.Menu;
import com.br.project.models.acesso.Submenu;

public class SubmenuForm {

	private Long idMenu;
	private String titulo;
	private String url;

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

	public Submenu cadastrar(Menu menu) {

		Submenu submenu = new Submenu();
		submenu.setIdMenu(menu);
		submenu.setTitulo(titulo);
		submenu.setUrl(url);

		return submenu;
	}

	public Submenu atualizar(Long id, Menu menu, Submenu submenu) {

		submenu.setIdMenu(menu);
		submenu.setTitulo(titulo);
		submenu.setUrl(url);

		return submenu;
	}
}
