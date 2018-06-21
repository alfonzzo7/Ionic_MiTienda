package com.pherrera.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pherrera.rest.entity.OrdenesDetalle;

public interface OrdenesDetalleRepository extends JpaRepository<OrdenesDetalle, Integer> {
	List<OrdenesDetalle> findByOrdenId (int ordenId);
}