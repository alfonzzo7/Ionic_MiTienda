package com.pherrera.rest.entity;
// Generated 20-jun-2018 8:46:29 by Hibernate Tools 3.5.0.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Productos generated by hbm2java
 */
@Entity
@Table(name = "productos", catalog = "tiendadb")
public class Productos implements java.io.Serializable {

	private String codigo;
	private String producto;
	private String linea;
	private int lineaId;
	private String proveedor;
	private String descripcion;
	private BigDecimal precioCompra;

	public Productos() {
	}

	public Productos(String codigo, String producto, String linea, int lineaId, String proveedor, String descripcion,
			BigDecimal precioCompra) {
		this.codigo = codigo;
		this.producto = producto;
		this.linea = linea;
		this.lineaId = lineaId;
		this.proveedor = proveedor;
		this.descripcion = descripcion;
		this.precioCompra = precioCompra;
	}

	@Id
	@Column(name = "codigo", unique = true, nullable = false, length = 15)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "producto", nullable = false, length = 70)
	public String getProducto() {
		return this.producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	@Column(name = "linea", nullable = false, length = 50)
	public String getLinea() {
		return this.linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	@Column(name = "linea_id", nullable = false)
	public int getLineaId() {
		return this.lineaId;
	}

	public void setLineaId(int lineaId) {
		this.lineaId = lineaId;
	}

	@Column(name = "proveedor", nullable = false, length = 50)
	public String getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	@Column(name = "descripcion", nullable = false, length = 65535)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "precio_compra", nullable = false, precision = 10)
	public BigDecimal getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

}
