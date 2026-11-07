package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcAccion;
import com.gem.sistema.business.domain.TcArea;

@Repository(value = "tcAccionRepository")
public interface TcAccionRepository extends PagingAndSortingRepository<TcAccion, Long> {

	@Query(value = "SELECT a FROM TcAccion a WHERE a.nombre LIKE %:criterio% or a.clave LIKE %:criterio%")
	List<TcAccion> findAccionesBy(@Param("criterio") String criterio);

	List<TcAccion> findByClaveContainingOrNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String clave,
			String nombre, String descripcion);
	
	List<TcAccion> findByDireccionOrderByClave(TcArea area);

}
