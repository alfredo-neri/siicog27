package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm5511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5511Service.
 *
 * @author Mateo
 */
public interface Pm5511Service {
	
	/**
	 * Find all order by mensual.
	 *
	 * @return the list
	 */
	List<Pm5511> findAllOrderByMensual();
	
	/**
	 * Order by asc.
	 *
	 * @return the sort
	 */
	Sort orderByAsc();
	
	/**
	 * Begin variables.
	 *
	 * @return the pm 5511
	 */
	Pm5511 beginVariables();
	
	/**
	 * Save.
	 *
	 * @param listPm511 the list pm 511
	 * @param index the index
	 * @return the list
	 */
	List<Pm5511> save(List<Pm5511> listPm511, Integer index);
	
	/**
	 * Delete.
	 *
	 * @param listPm511 the list pm 511
	 * @param index the index
	 * @return the list
	 */
	List<Pm5511> delete(List<Pm5511> listPm511, Integer index);
	
	/**
	 * Clean.
	 *
	 * @param listPm511 the list pm 511
	 * @param index the index
	 * @return the list
	 */
	List<Pm5511> clean(List<Pm5511> listPm511, Integer index);
	
	/**
	 * Cancel.
	 *
	 * @return the list
	 */
	List<Pm5511> cancel();
	
	/**
	 * Adds the.
	 *
	 * @return the pm 5511
	 */
	Pm5511 add();

}
