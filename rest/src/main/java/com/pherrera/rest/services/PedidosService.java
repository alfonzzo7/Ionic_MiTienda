package com.pherrera.rest.services;

import java.util.List;

import com.pherrera.rest.dto.EliminarPedidoDto;
import com.pherrera.rest.dto.PedidoDto;
import com.pherrera.rest.dto.PedidosUsuarioDto;
import com.pherrera.rest.dto.ProductosPedidoDto;
import com.pherrera.rest.entity.Ordenes;

public interface PedidosService {
	
	Ordenes nuevoPedido (PedidoDto pedido);
	
	Boolean agregarProductosPedido (Ordenes ordenes, ProductosPedidoDto productosPedidoDto);
	
	List<PedidosUsuarioDto> obtenerPedidosUsuario (PedidoDto pedido);
	
	Boolean comprobarOrden (EliminarPedidoDto eliminarPedidoDto);
	
	void borrarOrden (EliminarPedidoDto eliminarPedidoDto);
}
