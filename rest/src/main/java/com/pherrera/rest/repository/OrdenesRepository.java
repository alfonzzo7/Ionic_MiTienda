package com.pherrera.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pherrera.rest.entity.Ordenes;

public interface OrdenesRepository extends JpaRepository<Ordenes, Integer> {
	List<Ordenes> findByUsuarioId(int usuarioId);
	Ordenes findByIdAndUsuarioId(int id, int usuarioId);
}