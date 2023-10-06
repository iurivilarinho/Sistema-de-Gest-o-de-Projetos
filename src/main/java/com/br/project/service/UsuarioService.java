package com.br.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.project.models.Usuario;
import com.br.project.repository.PerfilRepository;
import com.br.project.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	@Value("${api.security.token.secret}")
	private String secret;

	public Usuario usuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			String username = authentication.getName();
			return usuarioRepository.findByLogin(username).orElse(null);
		}
		return null;
	}

	public void salvarPerfis(List<Long> idPerfis, Usuario usuario) {

		perfilRepository.saveAll(idPerfis.stream()
				.map(p -> perfilRepository.findById(p)
						.orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado!")))
				.collect(Collectors.toList()));
	}

	public Usuario getUsuarioPorToken(String tokenJWT) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("API Voll.med").build();
			DecodedJWT decodedJWT = verifier.verify(tokenJWT);

			String login = decodedJWT.getSubject(); // Assume que o subject contém o login do usuário

			Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow();

			return usuario;

		} catch (JWTDecodeException e) {
			// Token inválido
			throw new DataIntegrityViolationException("token invalido!");
		}

	}

}
