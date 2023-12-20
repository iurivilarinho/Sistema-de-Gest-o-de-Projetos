package com.br.project.models;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DocumentoMultipartFile implements MultipartFile {

	private final String nome;
	private final String contentType;
	private final byte[] documento;

	public DocumentoMultipartFile(String nome, String contentType, byte[] documento) {
		this.nome = nome;
		this.contentType = contentType;
		this.documento = documento;
	}

	@Override
	public String getName() {
		return nome;
	}

	@Override
	public String getOriginalFilename() {
		return nome;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return documento == null || documento.length == 0;
	}

	@Override
	public long getSize() {
		return documento.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return documento;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new java.io.ByteArrayInputStream(documento);
	}

	@Override
	public void transferTo(java.nio.file.Path dest) throws IOException, IllegalStateException {
		throw new UnsupportedOperationException("transferTo() is not supported for this implementation");
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		// TODO Auto-generated method stub

	}
}
