package com.pherrera.rest.entity;
// Generated 20-jun-2018 8:46:29 by Hibernate Tools 3.5.0.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Login generated by hbm2java
 */
@Entity
@Table(name = "login", catalog = "tiendadb")
public class Login implements java.io.Serializable {

	private Integer id;
	private String correo;
	private String contrasena;
	private String token;

	public Login() {
	}

	public Login(String correo, String contrasena, String token) {
		this.correo = correo;
		this.contrasena = contrasena;
		this.token = token;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "correo", nullable = false, length = 200)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "contrasena", nullable = false, length = 200)
	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Column(name = "token", nullable = false, length = 200)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
