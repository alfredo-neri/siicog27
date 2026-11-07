package com.gem.sistema.business.repository.catalogs;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gem.sistema.business.domain.Cuenta;

// TODO: Auto-generated Javadoc
/**
 * Interface para acceso de datos implementados manualmente.
 * 
 * @author Luis Sosa
 */
public interface CuentaRepositoryCustom {

	/**
	 * Find all by filters.
	 *
	 * @param filters the filters
	 * @param pageable the pageable
	 * @param count the count
	 * @return the page
	 */
	Page<Cuenta> findAllByFilters(Map<String, Object> filters, Pageable pageable, Integer count);

	/**
	 * Count.
	 *
	 * @param filters the filters
	 * @return the integer
	 */
	Integer count(Map<String, Object> filters);
}