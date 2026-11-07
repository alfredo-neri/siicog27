package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Proveedor;
import com.gem.sistema.business.domain.ProveedorNuevo;

/**
 * The Interface ProveedoresRepository.
 */
@Repository(value = "proveedoresRepository")
public interface ProveedoresRepository
		extends PagingAndSortingRepository<ProveedorNuevo, Long>, QueryDslPredicateExecutor<ProveedorNuevo> {

	List<ProveedorNuevo> findAll();

	@Query(value = "SELECT * FROM 	TC_PROVEEDORES P WHERE "
			+ "	LOWER(P.NOMBRE_PROVEEDOR)  LIKE %:keyword% OR 	LOWER(P.RFC) LIKE %:keyword% OR "
			+ "	LOWER(P.DOMICILIO) LIKE %:keyword% OR LOWER(P.TELEFONO) LIKE %:keyword% OR "
			+ "LOWER(P.TIPO) LIKE %:keyword% ", nativeQuery = true)
	List<ProveedorNuevo> findAllByFiltro(@Param("keyword") String keyword);

}
