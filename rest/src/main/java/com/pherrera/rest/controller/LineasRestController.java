package com.pherrera.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pherrera.rest.entity.Lineas;
import com.pherrera.rest.services.LineasService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/lineas"})
public class LineasRestController {
	
	@Autowired
    private LineasService lineasService;
		
	@GetMapping
    public List<Lineas> getLineas(){
        return lineasService.findAll();
    }
}
