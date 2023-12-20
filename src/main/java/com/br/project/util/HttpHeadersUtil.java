package com.br.project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class HttpHeadersUtil {

	private DateTimeFormatter formatoDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public HttpHeaders excel(String nomeArquivo) {

		HttpHeaders headers2 = new HttpHeaders();
		headers2.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers2.setContentDispositionFormData("attachment",
				nomeArquivo + LocalDateTime.now().format(formatoDateTime) + ".xlsx");

		// Configurar o tipo de conteúdo como Excel
		headers2.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		return headers2;
	}

	public HttpHeaders pdf(String nomeArquivo) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment",
				nomeArquivo + LocalDateTime.now().format(formatoDateTime) + ".pdf");

		return headers;
	}

}
