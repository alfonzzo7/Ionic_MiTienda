package com.pherrera.rest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pherrera.rest.entity.Login;
import com.pherrera.rest.repository.LoginRepository;
import com.pherrera.rest.services.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
    private LoginRepository loginRepository;

    @Override
    public Login create(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public void delete(int id) {
        loginRepository.deleteById(id);
    }

    @Override
    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Optional<Login> findById(int id) {
    	return loginRepository.findById(id);
    }

    @Override
    public Login update(Login login) {
    	return loginRepository.save(login);
    }

	@Override
	public Login findLogin(String correo, String contrasena) {
		return loginRepository.findByCorreoAndContrasena(correo, contrasena);
	}

	@Override
	public Login findByIdAndToken(Integer id, String token) {
		return loginRepository.findByIdAndToken(id, token);
	}

}
