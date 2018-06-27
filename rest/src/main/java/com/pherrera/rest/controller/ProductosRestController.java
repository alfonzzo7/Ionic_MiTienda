package com.pherrera.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pherrera.rest.Constantes;
import com.pherrera.rest.entity.Productos;
import com.pherrera.rest.services.ProductosService;

@CrossOrigin(origins = Constantes.URL_ALLOW_ORIGIN, maxAge = 3600)
@RestController
@RequestMapping({"/productos"})
public class ProductosRestController {
	
	@Autowired
    private ProductosService productosService;
		
	@GetMapping(params = {"pagina", "limite"})
    public ResponseEntity<Object> getProductsPage(@RequestParam("pagina") int pagina, @RequestParam("limite") int limite){
		List<Productos> productosList = productosService.findPag(pagina, limite);
		if(productosList != null && productosList.size() > 0) {
			return ResponseEntity.ok(productosList);
		}else{
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
    }
	
	@GetMapping(path = {"/linea"}, params = {"linea", "pagina", "limite"})
    public List<Productos> getProductsByLineaPage(@RequestParam("linea") int linea, @RequestParam("pagina") int pagina, @RequestParam("limite") int limite){
		return productosService.findByLineaPag(linea, pagina, limite);
    }
	
	@GetMapping(path = {"/busqueda"}, params = {"termino"})
    public List<Productos> getProduct(@RequestParam("termino") String termino){
        return productosService.findByTermino(termino);
    }
}
