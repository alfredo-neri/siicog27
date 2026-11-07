package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Cuenta;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


// TODO: Auto-generated Javadoc
/**
 * The Interface CuentaService.
 *
 * @author Gerardo CUERO
 */
public interface CuentaService {
    
    /**
     * Find all.
     *
     * @return the list
     */
    List<Cuenta> findAll();
        
    /**
     * Find all.
     *
     * @param pageRequest the page request
     * @return the page
     */
    Page<Cuenta> findAll(Pageable pageRequest);

	/**
	 * Save.
	 *
	 * @param cuenta the cuenta
	 * @return the cuenta
	 */
	Cuenta save(Cuenta cuenta);

	/**
	 * Find cuenta by id.
	 *
	 * @param id the id
	 * @return the cuenta
	 */
	Cuenta findCuentaById(Long id);
    
	/**
	 * Delete cuenta.
	 *
	 * @param cuenta the cuenta
	 */
	void deleteCuenta(Cuenta cuenta);
  
	/**
	 * Find by description.
	 *
	 * @param searchTerm the search term
	 * @param pageRequest the page request
	 * @return the page
	 */
	Page<Cuenta> findByDescription(String searchTerm, Pageable pageRequest);    
    
    /**
     * Find all by filters.
     *
     * @param filters the filters
     * @param pageRequest the page request
     * @param count the count
     * @return the page
     */
    Page<Cuenta> findAllByFilters(Map<String, Object> filters, Pageable pageRequest, Integer count);
    
    /**
     * Count.
     *
     * @param filters the filters
     * @return the integer
     */
    Integer count(Map<String, Object> filters);
    
    /**
     * Gets the where from filters.
     *
     * @param filters the filters
     * @return the where from filters
     */
    String getWhereFromFilters(Map<String, Object> filters);
    
    
}
