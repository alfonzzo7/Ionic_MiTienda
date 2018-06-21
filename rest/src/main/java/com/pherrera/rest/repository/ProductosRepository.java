package com.pherrera.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pherrera.rest.entity.Productos;

public interface ProductosRepository extends JpaRepository<Productos, String> {
	@Query(value = "SELECT * FROM Productos p WHERE p.linea_id = :lineaId LIMIT :pagina,:limite", nativeQuery = true)
	List<Productos> findByLineaId(@Param("lineaId") int lineaId, @Param("pagina") int pagina, @Param("limite") int limite);
	
	@Query(value = "SELECT * FROM Productos p WHERE p.producto like %:termino%", nativeQuery = true)
	List<Productos> findByTermino(@Param("termino") String termino);
	
	@Query(value = "SELECT p.* FROM Productos p, Ordenes_detalle od WHERE p.codigo = od.producto_id AND od.orden_id = :ordenId", nativeQuery = true)
	List<Productos> findByPedidoId(@Param("ordenId") int ordenId);
}