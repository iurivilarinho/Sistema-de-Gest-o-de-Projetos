package com.br.project.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.form.RelatorioAtivoForm;
import com.br.project.service.RelatorioAtivoService;
import com.br.project.util.HttpHeadersUtil;

@RestController
@RequestMapping("/relatorio_ativo")
public class RelatorioAtivoController {

	@Autowired
	private RelatorioAtivoService relatorioAtivoService;

	@Autowired
	private HttpHeadersUtil httpHeadersUtil;

	@PostMapping("/{aquisicao}")
	public ResponseEntity<byte[]> gerarRelatorio(@RequestBody RelatorioAtivoForm form, @PathVariable Boolean aquisicao)
			throws IOException {

		if (aquisicao) {
			return new ResponseEntity<byte[]>(relatorioAtivoService.relatorioAquisicao(form), httpHeadersUtil.pdf(null),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]>(relatorioAtivoService.relatorioDevolucao(form), httpHeadersUtil.pdf(null),
					HttpStatus.OK);
		}

	}

}
