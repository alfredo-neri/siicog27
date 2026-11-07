package com.gem.sistema.business.service.catalogos;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gem.sistema.business.domain.Natgas;

// TODO: Auto-generated Javadoc
/**
 * The Interface NatgasService.
 *
 * @author Gerardo CUERO
 */
public interface NatgasService {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Natgas> findAll();

	/**
	 * Find all.
	 *
	 * @param pageRequest the page request
	 * @return the page
	 */
	Page<Natgas> findAll(Pageable pageRequest);

	/**
	 * Find all by filters.
	 *
	 * @param filters the filters
	 * @param pageRequest the page request
	 * @param count the count
	 * @return the page
	 */
	Page<Natgas> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count);

	/**
	 * Count.
	 *
	 * @param filters the filters
	 * @return the integer
	 */
	Integer count(Map<String, Object> filters);

}
