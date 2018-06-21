package com.pherrera.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pherrera.rest.entity.Productos;
import com.pherrera.rest.services.ProductosService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/productos"})
public class ProductosRestController {
	
	@Autowired
    private ProductosService productosService;
		
	@GetMapping(params = {"pagina", "limite"})
    public List<Productos> getProductsPage(@RequestParam("pagina") int pagina, @RequestParam("limite") int limite){
		return productosService.findPag(pagina, limite);
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
