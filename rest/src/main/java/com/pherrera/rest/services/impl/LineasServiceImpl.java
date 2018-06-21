package com.pherrera.rest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pherrera.rest.entity.Lineas;
import com.pherrera.rest.repository.LineasRepository;
import com.pherrera.rest.services.LineasService;

@Service("lineasService")
public class LineasServiceImpl implements LineasService {

	@Autowired
    private LineasRepository lineasRepository;

    @Override
    public Lineas create(Lineas lineas) {
        return lineasRepository.save(lineas);
    }

    @Override
    public void delete(int id) {
        lineasRepository.deleteById(id);
    }

    @Override
    public List<Lineas> findAll() {
        return lineasRepository.findAll();
    }

    @Override
    public Optional<Lineas> findById(int id) {
    	return lineasRepository.findById(id);
    }

    @Override
    public Lineas update(Lineas lineas) {
    	return lineasRepository.save(lineas);
    }

}
