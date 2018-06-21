package com.pherrera.rest.dto;

import java.util.List;

import com.pherrera.rest.entity.Ordenes;
import com.pherrera.rest.entity.Productos;

public class PedidosUsuarioDto implements java.io.Serializable {
	
	private static final long serialVersionUID = 8282502082574133041L;
	
	private Ordenes ordenes;
	private List<Productos> productos;

	public PedidosUsuarioDto() {
	}

	public PedidosUsuarioDto(Ordenes ordenes, List<Productos> productos) {
		super();
		this.ordenes = ordenes;
		this.productos = productos;
	}

	public Ordenes getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(Ordenes ordenes) {
		this.ordenes = ordenes;
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}
}
