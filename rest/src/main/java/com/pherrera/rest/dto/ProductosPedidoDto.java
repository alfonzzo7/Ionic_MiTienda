package com.pherrera.rest.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pherrera.rest.entity.Productos;

public class ProductosPedidoDto extends PedidoDto implements java.io.Serializable {
	
	private static final long serialVersionUID = 8282502082574133041L;
	
	private List<Productos> productos;

	public ProductosPedidoDto() {
	}

	public ProductosPedidoDto(List<Productos> productos) {
		this.productos = productos;
	}
	
	@NotNull
	@Size(min = 1)
	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}
	
}
