package com.pherrera.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pherrera.rest.Constantes;
import com.pherrera.rest.dto.EliminarPedidoDto;
import com.pherrera.rest.dto.PedidoDto;
import com.pherrera.rest.dto.PedidosUsuarioDto;
import com.pherrera.rest.dto.ProductosPedidoDto;
import com.pherrera.rest.entity.Login;
import com.pherrera.rest.entity.Ordenes;
import com.pherrera.rest.services.LoginService;
import com.pherrera.rest.services.PedidosService;

@CrossOrigin(origins = Constantes.URL_ALLOW_ORIGIN, maxAge = 3600)
@RestController
@RequestMapping({"/pedido"})
public class PedidosRestController {
	
	@Autowired
    private LoginService loginService;
	
	@Autowired
    private PedidosService pedidosService; 
		
	@PostMapping(path = {"/nuevo"})
    public ResponseEntity<Object> nuevo(@Valid @RequestBody ProductosPedidoDto productosPedidoDto){
		Login login = loginService.findByIdAndToken(Integer.parseInt(productosPedidoDto.getIdUsuario()), productosPedidoDto.getToken());
		if(login != null) {
			Ordenes ordenes = pedidosService.nuevoPedido(productosPedidoDto);
			if(ordenes != null && ordenes.getId() != null) {
				if(pedidosService.agregarProductosPedido(ordenes, productosPedidoDto)) {
					return ResponseEntity.ok(ordenes);
				}else{
					return ResponseEntity.status(HttpStatus.NO_CONTENT).header("error", "Alguno de los productos del pedido no existen").build();
				}
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error", "Ocurrio un error al crear la orden").build();
			}
		}else{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("error", "Id de usuario y/o token no son validos").build();
		}
    }
	
	@PostMapping(path = {"/obtener"})
    public ResponseEntity<Object> obtenerPedidosUsuario(@Valid @RequestBody PedidoDto pedidoDto){
		Login login = loginService.findByIdAndToken(Integer.parseInt(pedidoDto.getIdUsuario()), pedidoDto.getToken());
		if(login != null) {
			List<PedidosUsuarioDto> pedidosUsuarioDtoList = pedidosService.obtenerPedidosUsuario(pedidoDto);
			if(pedidosUsuarioDtoList != null && pedidosUsuarioDtoList.size() > 0) {
				return ResponseEntity.ok(pedidosUsuarioDtoList);
			}else{
				return ResponseEntity.status(HttpStatus.NO_CONTENT).header("error", "El usuario no tiene pedidos").build();
			}
		}else{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("error", "Id de usuario y/o token no son validos").build();
		}
    }
	
	@DeleteMapping(path = {"/eliminar"})
    public ResponseEntity<Object> eliminarPedidoUsuario(@Valid @RequestBody EliminarPedidoDto eliminarPedidoDto){
		Login login = loginService.findByIdAndToken(Integer.parseInt(eliminarPedidoDto.getIdUsuario()), eliminarPedidoDto.getToken());
		if(login != null) {
			if(pedidosService.comprobarOrden(eliminarPedidoDto)) {
				pedidosService.borrarOrden(eliminarPedidoDto);
				return ResponseEntity.ok("");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error", "No se puede eliminar la orden").build();
			}
		}else{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("error", "Id de usuario y/o token no son validos").build();
		}
    }
}
