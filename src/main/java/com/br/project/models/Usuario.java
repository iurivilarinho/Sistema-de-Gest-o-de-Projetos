package com.br.project.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.project.models.acesso.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbUsuario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_img")
	private Documento imagem;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbUsuarioPerfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private Set<Perfil> perfis = new HashSet<>();

	public Usuario() {

	}

	public Usuario(String login, String nome, String email, String senha, String cpf, String celular,
			Documento imagem) {
		this.login = login;
		this.nome = nome;
		this.email = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		this.cpf = cpf;
		this.celular = celular;
		this.status = true;
		this.imagem = imagem;
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

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Documento getImagem() {
		return imagem;
	}

	public void setImagem(Documento imagem) {
		this.imagem = imagem;
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

	@Override
	public int hashCode() {
		return Objects.hash(celular, cpf, email, id, login, nome, perfis, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(celular, other.celular) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(login, other.login) && Objects.equals(nome, other.nome)
				&& Objects.equals(perfis, other.perfis) && Objects.equals(status, other.status);
	}

}
