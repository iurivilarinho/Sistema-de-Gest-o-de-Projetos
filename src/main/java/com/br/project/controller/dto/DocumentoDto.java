package com.br.project.controller.dto;

import java.util.Objects;

import com.br.project.models.Documento;

public class DocumentoDto {

	private Long id;
	private String nome;
	private String contentType;

	public DocumentoDto() {

	}

	public DocumentoDto(Documento documento) {
		this.id = documento.getId();
		this.nome = documento.getNome();
		this.contentType = documento.getContentType();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentType, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoDto other = (DocumentoDto) obj;
		return Objects.equals(contentType, other.contentType) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome);
	}

}
