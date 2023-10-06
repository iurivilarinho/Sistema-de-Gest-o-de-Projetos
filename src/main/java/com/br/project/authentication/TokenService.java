package com.br.project.authentication;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.project.models.Usuario;
import com.br.project.repository.UsuarioRepository;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public String gerarToken(Usuario usuario) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API Voll.med").withSubject(usuario.getLogin())
					.withExpiresAt(dataExpiracao()).sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("erro ao gerar token jwt", exception);
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo).withIssuer("API Voll.med").build().verify(tokenJWT).getSubject();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token JWT inválido ou expirado!");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
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
