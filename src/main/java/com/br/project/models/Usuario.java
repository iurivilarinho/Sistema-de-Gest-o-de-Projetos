package com.br.project.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.project.models.acesso.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbUsuario")
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String nome;

	@JsonIgnore
	private String senha;

	private String cpf;

	private String celular;

	private String email;

	private Boolean status;

	private Long idEmpresa;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tbUsuarioPerfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	@Fetch(FetchMode.JOIN)
	private Set<Perfil> perfis = new HashSet<>();

	public Usuario() {

	}

	public Usuario(String login, String nome, String email, String senha, String cpf, String celular, Boolean status,
			Long idEmpresa) {
		this.login = login;
		this.nome = nome;
		this.email = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		this.cpf = cpf;
		this.celular = celular;
		this.status = status;
		this.idEmpresa = idEmpresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Stub de método gerado automaticamente
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Stub de método gerado automaticamente
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Stub de método gerado automaticamente
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Stub de método gerado automaticamente
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Stub de método gerado automaticamente
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Stub de método gerado automaticamente
		return true;
	}

}
