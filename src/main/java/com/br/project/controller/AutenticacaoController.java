package com.br.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.project.authentication.LoginForm;
import com.br.project.authentication.TokenDto;
import com.br.project.authentication.TokenService;
import com.br.project.models.Usuario;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> efetuarLogin(@RequestBody LoginForm dados) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
		var authentication = manager.authenticate(authenticationToken);

		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		Usuario usuario = tokenService.getUsuarioPorToken(tokenJWT);
		System.out.println(usuario.getId());

		return ResponseEntity.ok(new TokenDto(tokenJWT, "Bearer", usuario.getId()));
	}

}
