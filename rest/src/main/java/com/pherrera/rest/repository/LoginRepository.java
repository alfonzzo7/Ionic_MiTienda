package com.pherrera.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pherrera.rest.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
	Login findByCorreoAndContrasena(String correo, String contrasena);
	
	Login findByIdAndToken(Integer id, String token);
}