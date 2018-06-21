package com.pherrera.rest.services;

import java.util.List;
import java.util.Optional;

import com.pherrera.rest.entity.Productos;

public interface ProductosService {
	
    public Productos create(Productos productos);

    
    public void delete(String id);

    
    public List<Productos> findAll();
    
    
    public List<Productos> findPag(int pagina, int limite);
    
    
    public List<Productos> findByLineaPag(int lineaId, int pagina, int limite);
    
    
    public List<Productos> findByTermino(String termino);

    
    public Optional<Productos> findById(String id);

    
    public Productos update(Productos productos);
}
