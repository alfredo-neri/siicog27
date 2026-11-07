package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.ProveedorNuevo;

/**
 * The Interface ProveedoresRepository.
 */
@Repository(value = "proveedoresNuevoRepository")
public interface ProveedoresNuevoRepository
		extends PagingAndSortingRepository<ProveedorNuevo, Long>, QueryDslPredicateExecutor<ProveedorNuevo> {

	List<ProveedorNuevo> findAll();

	ProveedorNuevo findOneByCurp(String curp);

	ProveedorNuevo findOneByRfc(String rfc);

}
