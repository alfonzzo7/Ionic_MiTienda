package com.pherrera.rest.services;

import java.util.List;
import java.util.Optional;

import com.pherrera.rest.entity.Lineas;

public interface LineasService {
	
    public Lineas create(Lineas lineas);

    
    public void delete(int id);

    
    public List<Lineas> findAll();

    
    public Optional<Lineas> findById(int id);

    
    public Lineas update(Lineas lineas);
}
