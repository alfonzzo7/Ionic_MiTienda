package com.pherrera.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pherrera.rest.Constantes;
import com.pherrera.rest.dto.LoginDto;
import com.pherrera.rest.entity.Login;
import com.pherrera.rest.services.LoginService;

@CrossOrigin(origins = Constantes.URL_ALLOW_ORIGIN, maxAge = 3600)
@RestController
@RequestMapping({"/login"})
public class LoginRestController {
	
	@Autowired
    private LoginService loginService;
		
	@PostMapping
    public ResponseEntity<Object> getLogin(@Valid @RequestBody LoginDto loginDto){
		Login login = loginService.findLogin(loginDto.getCorreo(), loginDto.getContrasena());
		
		if(login != null) {
			String token = JWT.create().withSubject(loginDto.getCorreo()).sign(Algorithm.none());
			
			login.setToken(token);
			loginService.update(login);
			
			return ResponseEntity.ok(login);
		}else{
			return ResponseEntity.noContent().build();
		}
    }
}
