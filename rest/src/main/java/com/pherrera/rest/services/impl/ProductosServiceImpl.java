package com.pherrera.rest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pherrera.rest.entity.Productos;
import com.pherrera.rest.repository.ProductosRepository;
import com.pherrera.rest.services.ProductosService;

@Service("productosService")
public class ProductosServiceImpl implements ProductosService {

	@Autowired
    private ProductosRepository productosRepository;

    @Override
    public Productos create(Productos productos) {
        return productosRepository.save(productos);
    }

    @Override
    public void delete(String id) {
        productosRepository.deleteById(id);
    }

    @Override
    public List<Productos> findAll() {
        return productosRepository.findAll();
    }
    
    @Override
    public List<Productos> findPag(int pagina, int limite) {
    	List<Productos> productosList = null;
    	PageRequest request = PageRequest.of(pagina - 1, limite, Sort.Direction.ASC, "codigo");
        Page<Productos> pagProductos = productosRepository.findAll(request);
        if(pagProductos.hasContent()) {
        	productosList = pagProductos.getContent();
        }	
        return productosList;
    }

    @Override
    public Optional<Productos> findById(String id) {
    	return productosRepository.findById(id);
    }

    @Override
    public Productos update(Productos productos) {
    	return productosRepository.save(productos);
    }

	@Override
	public List<Productos> findByLineaPag(int lineaId, int pagina, int limite) {
		pagina = pagina * limite;
		return productosRepository.findByLineaId(lineaId, pagina, limite);
	}

	@Override
	public List<Productos> findByTermino(String termino) {
		return productosRepository.findByTermino(termino);
	}

}
