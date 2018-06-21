package com.pherrera.rest.dto;

import javax.validation.constraints.NotBlank;

public class PedidoDto implements java.io.Serializable {
	
	private static final long serialVersionUID = 8282502082574133041L;
	
	private String token;
	private String idUsuario;

	public PedidoDto() {
	}

	public PedidoDto(String token, String idUsuario) {
		this.token = token;
		this.idUsuario = idUsuario;
	}

	@NotBlank
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@NotBlank
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
