package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm0511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0511Service.
 *
 * @author Mateo
 */
public interface Pm0511Service {

	/**
	 * Adds the.
	 *
	 * @return the pm 0511
	 */
	Pm0511 add();

	/**
	 * Save.
	 *
	 * @param pm0511 the pm 0511
	 * @return the list
	 */
	List<Pm0511> save(Pm0511 pm0511);

	/**
	 * Modify.
	 *
	 * @param pm0511 the pm 0511
	 * @return the list
	 */
	Pm0511 modify(Pm0511 pm0511, Integer oldValue);

	/**
	 * Delete.
	 *
	 * @param pm0511 the pm 0511
	 * @return the list
	 */
	List<Pm0511> delete(Pm0511 pm0511);

	/**
	 * Order by asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0511> orderByAsc(Integer idSector);

	/**
	 * Gets the sort.
	 *
	 * @return the sort
	 */
	Sort getSort();

	/**
	 * Gets the tri month.
	 *
	 * @return the tri month
	 */
	List<String> getTriMonth();

	/**
	 * Execute query.
	 *
	 * @param parameter1 the parameter 1
	 * @param parameter2 the parameter 2
	 * @param filter1 the filter 1
	 * @param filter2 the filter 2
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0511> executeQuery(Integer parameter1, String parameter2, String filter1, String filter2, Integer idSector);
}
