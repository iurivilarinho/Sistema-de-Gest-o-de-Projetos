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

	@Value("${api.jwt.expiration}")
	private String expiration;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public String gerarToken(Usuario usuario) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API Voll.med").withClaim("login", usuario.getLogin())
					.withClaim("id", usuario.getId()).withExpiresAt(dataExpiracao()).sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token JWT", exception);
		}
	}

	public String getSubject(String tokenJWT) {

		var algoritmo = Algorithm.HMAC256(secret);
		return JWT.require(algoritmo).withIssuer("API Voll.med").build().verify(tokenJWT).getSubject();

	}
	
	public String getClaim(String tokenJWT) {
	    try {
	        var algoritmo = Algorithm.HMAC256(secret);
	        return JWT.require(algoritmo)
	                .withIssuer("API Voll.med")
	                .build()
	                .verify(tokenJWT)
	                .getClaim("login")
	                .asString();
	    } catch (JWTVerificationException e) {
	        // Token inválido
	        throw new DataIntegrityViolationException("Token inválido!");
	    }
	}

	public boolean isTokenValido(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algoritmo).withIssuer("API Voll.med").build();
			verifier.verify(tokenJWT);
			return true;
		} catch (JWTVerificationException e) {
			return false;
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(Integer.parseInt(expiration)).toInstant(ZoneOffset.of("-03:00"));
	}

	public Usuario getUsuarioPorToken(String tokenJWT) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("API Voll.med").build();
			DecodedJWT decodedJWT = verifier.verify(tokenJWT);

			String login = decodedJWT.getClaim("login").asString();

			Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow();

			return usuario;

		} catch (JWTDecodeException e) {
			// Token inválido
			throw new DataIntegrityViolationException("token invalido!");
		}

	}

}
