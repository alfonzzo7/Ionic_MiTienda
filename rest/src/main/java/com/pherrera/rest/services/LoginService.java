package com.pherrera.rest.services;

import java.util.List;
import java.util.Optional;

import com.pherrera.rest.entity.Login;

public interface LoginService {
	
    public Login create(Login login);

    
    public void delete(int id);

    
    public List<Login> findAll();

    
    public Optional<Login> findById(int id);

    
    public Login update(Login login);
    
    
    public Login findLogin(String correo, String contrasena);
    
    public Login findByIdAndToken(Integer id, String token);
}
