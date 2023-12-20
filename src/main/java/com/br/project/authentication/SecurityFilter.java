package com.br.project.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.project.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getRequestURI().startsWith("/swagger")
				|| request.getRequestURI().startsWith("/projeto") && request.getMethod().equals("GET")
				|| request.getRequestURI().startsWith("/v3")
				|| request.getRequestURI().equals("/login") && request.getMethod().equals("POST")) {
			filterChain.doFilter(request, response);
			return;
		}
		var tokenJWT = recuperarToken(request);

		if (tokenJWT != null && tokenService.isTokenValido(tokenJWT)) {
			var subject = tokenService.getClaim(tokenJWT);
			var usuario = usuarioRepository.findByLogin(subject);

			if (usuario != null) {
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
						usuario.get().getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				handleUsuarioNaoEncontrado(response);
				return;
			}
		} else {
			handleTokenInvalido(response);
			return;
		}

		filterChain.doFilter(request, response);
	}

	private void handleTokenInvalido(HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter writer = response.getWriter()) {
			writer.write("{ \"message\": \"Token inválido ou expirado! Entre novamente.\", ");
			writer.write("\"timestamp\": \"" + LocalDateTime.now() + "\" }"); // Correção aqui
			writer.flush();
		} catch (IOException e) {
			// Lidar com a exceção, se necessário
			e.printStackTrace(); // Imprima a exceção ou trate de acordo com suas necessidades
		}

	}

	private void handleUsuarioNaoEncontrado(HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json;charset=UTF-8");

		try (PrintWriter writer = response.getWriter()) {
			writer.write("{ \"error\": \"Usuário não encontrado\" }");
			writer.flush();
		} catch (IOException e) {
			// Lidar com a exceção, se necessário
		}
	}

	private String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.replace("Bearer ", "");
		}

		return null;
	}
}
