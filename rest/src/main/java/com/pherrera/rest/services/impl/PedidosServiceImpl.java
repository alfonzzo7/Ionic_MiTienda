package com.pherrera.rest.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pherrera.rest.dto.EliminarPedidoDto;
import com.pherrera.rest.dto.PedidoDto;
import com.pherrera.rest.dto.PedidosUsuarioDto;
import com.pherrera.rest.dto.ProductosPedidoDto;
import com.pherrera.rest.entity.Ordenes;
import com.pherrera.rest.entity.OrdenesDetalle;
import com.pherrera.rest.entity.Productos;
import com.pherrera.rest.repository.OrdenesDetalleRepository;
import com.pherrera.rest.repository.OrdenesRepository;
import com.pherrera.rest.repository.ProductosRepository;
import com.pherrera.rest.services.PedidosService;

@Service("pedidosService")
public class PedidosServiceImpl implements PedidosService {

	@Autowired
    private OrdenesRepository ordenesRepository;
	
	@Autowired
    private OrdenesDetalleRepository ordenesDetalleRepository;
	
	@Autowired
    private ProductosRepository productosRepository;

	@Override
	public Ordenes nuevoPedido(PedidoDto pedido) {
		Ordenes ordenes = new Ordenes(Integer.parseInt(pedido.getIdUsuario()), new Date());
		return ordenesRepository.save(ordenes);
	}

	@Override
	public Boolean agregarProductosPedido(Ordenes ordenes, ProductosPedidoDto productosPedidoDto) {
		boolean pedidoCorrecto = true;
		OrdenesDetalle ordenesDetalle = null;
		List<OrdenesDetalle> ordenesDetallesList = new ArrayList<OrdenesDetalle>();
		for(Productos productos : productosPedidoDto.getProductos()) {
			Optional<Productos> producto = productosRepository.findById(productos.getCodigo());
			if(producto.isPresent()) {
				ordenesDetalle = new OrdenesDetalle();
				ordenesDetalle.setOrdenId(ordenes.getId());
				ordenesDetalle.setProductoId(productos.getCodigo());
				ordenesDetalleRepository.save(ordenesDetalle);
				ordenesDetallesList.add(ordenesDetalle);
			}else{
				ordenesDetalleRepository.deleteAll(ordenesDetallesList);
				ordenesRepository.delete(ordenes);
				pedidoCorrecto = false;
				break;
			}
		}
		
		return pedidoCorrecto;
	}

	@Override
	public List<PedidosUsuarioDto> obtenerPedidosUsuario(PedidoDto pedido) {		
		List<PedidosUsuarioDto> pedidosUsuarioDtoList = new ArrayList<PedidosUsuarioDto>();
		List<Ordenes> ordenesList = ordenesRepository.findByUsuarioId(Integer.parseInt(pedido.getIdUsuario()));
		for(Ordenes ordenes : ordenesList) {
			pedidosUsuarioDtoList.add(new PedidosUsuarioDto(ordenes, productosRepository.findByPedidoId(ordenes.getId())));
		}
		return pedidosUsuarioDtoList;
	}

	@Override
	public Boolean comprobarOrden(EliminarPedidoDto eliminarPedidoDto) {
		boolean borrable = false;
		Ordenes ordenes = ordenesRepository.findByIdAndUsuarioId(Integer.parseInt(eliminarPedidoDto.getIdOrden()), Integer.parseInt(eliminarPedidoDto.getIdUsuario()));
		if(ordenes != null) {
			borrable = true;
		}
		return borrable;
	}

	@Override
	public void borrarOrden(EliminarPedidoDto eliminarPedidoDto) {
		ordenesDetalleRepository.deleteAll(ordenesDetalleRepository.findByOrdenId(Integer.parseInt(eliminarPedidoDto.getIdOrden())));
		ordenesRepository.deleteById(Integer.parseInt(eliminarPedidoDto.getIdOrden()));
	}

}
