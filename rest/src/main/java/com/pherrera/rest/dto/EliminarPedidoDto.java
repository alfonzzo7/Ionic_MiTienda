package com.pherrera.rest.dto;

import javax.validation.constraints.NotBlank;

public class EliminarPedidoDto extends PedidoDto implements java.io.Serializable {
	
	private static final long serialVersionUID = 8282502082574133041L;
	
	private String idOrden;

	public EliminarPedidoDto() {
	}

	public EliminarPedidoDto(String idOrden) {
		this.idOrden = idOrden;
	}
	
	@NotBlank
	public String getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(String idOrden) {
		this.idOrden = idOrden;
	}
	
}
