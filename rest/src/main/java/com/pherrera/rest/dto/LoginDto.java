package com.pherrera.rest.dto;

import javax.validation.constraints.NotBlank;

public class LoginDto implements java.io.Serializable {
	
	private static final long serialVersionUID = 8282502082574133041L;
	
	private String correo;
	private String contrasena;

	public LoginDto() {
	}

	public LoginDto(String correo, String contrasena) {
		this.correo = correo;
		this.contrasena = contrasena;
	}

	@NotBlank
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@NotBlank
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
}
