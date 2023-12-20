package com.br.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException; // Importe esta classe
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
		try {
			var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
			var authentication = manager.authenticate(authenticationToken);

			var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
			Usuario usuario = tokenService.getUsuarioPorToken(tokenJWT);

			return ResponseEntity.ok(new TokenDto(tokenJWT, "Bearer", usuario));
		} catch (BadCredentialsException e) {
			throw new AccessDeniedException("Senha incorreta! Tente novamente.");
		}
	}
}
