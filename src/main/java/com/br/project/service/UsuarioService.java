package com.br.project.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.project.controller.dto.UsuarioDto;
import com.br.project.filtros.UsuarioFilros;
import com.br.project.form.UsuarioForm;
import com.br.project.models.Usuario;
import com.br.project.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DocumentoService documentoService;

	@Value("${api.security.token.secret}")
	private String secret;

	@Autowired
	private UsuarioFilros usuarioFilros;

	@Transactional(readOnly = true)
	public Usuario usuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Object principal = authentication.getPrincipal();

		if (principal instanceof Optional) {
			Optional<?> principalOptional = (Optional<?>) principal;

			if (principalOptional.isPresent() && principalOptional.get() instanceof Usuario) {
				Usuario usuario = (Usuario) principalOptional.get();
				return usuario;
			}
		}

		// Lidar com o caso em que o principal não é um Optional<Usuario> válido
		return null;
	}

	@Transactional(readOnly = true)
	public Usuario getUsuarioPorToken(String tokenJWT) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("API Voll.med").build();
			DecodedJWT decodedJWT = verifier.verify(tokenJWT);
			String id = decodedJWT.getClaim("id").toString();

			Usuario usuario = usuarioRepository.findById(Long.parseLong(id))
					.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado para o id: " + id));
			return usuario;
		} catch (JWTDecodeException e) {
			throw new DataIntegrityViolationException("token invalido!");
		}
	}

	@Transactional
	public Usuario cadastrar(UsuarioForm form, MultipartFile img) throws IOException {
		Usuario usuario = new Usuario(form.getLogin(), form.getNome(), form.getEmail(), form.getSenha(), form.getCpf(),
				form.getCelular(), documentoService.converterEmDocumento(img));
		if (form.getPerfis() != null) {
			usuario.getPerfis().addAll(form.getPerfis());
		}
		usuarioRepository.save(usuario);
		return usuario;
	}

	@Transactional
	public Usuario atualizar(Long idUsuario, UsuarioForm form, MultipartFile img) throws IOException {
		Usuario usuario = buscarUsuarioPorId(idUsuario);

		usuario.setLogin(form.getLogin());
		usuario.setNome(form.getNome());
		usuario.setSenha(form.getSenha());
		usuario.setCpf(form.getCpf());
		usuario.setCelular(form.getCelular());
		usuario.setEmail(form.getEmail());
		usuario.setImagem(documentoService.converterEmDocumento(img));

		if (form.getPerfis() != null) {
			usuario.getPerfis().removeIf(perfil -> !form.getPerfis().contains(perfil));
			usuario.getPerfis().addAll(form.getPerfis());
		}
		usuarioRepository.save(usuario);
		return usuario;
	}

	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorId(Long idUsuario) {
		return usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o ID: " + idUsuario));
	}

	@Transactional(readOnly = true)
	public Page<UsuarioDto> buscar(Boolean vinculados, String search, Pageable paginacao) {
		return usuarioFilros.filtro(vinculados, search, paginacao).map(UsuarioDto::new);
	}

	@Transactional
	public void ativarDesativar(Long idUsuario, Boolean status) {

		Usuario usuario = buscarUsuarioPorId(idUsuario);
		usuario.setStatus(status);
		usuarioRepository.save(usuario);
	}

}
