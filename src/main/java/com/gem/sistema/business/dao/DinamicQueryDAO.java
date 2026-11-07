package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.domain.Pm0511;

// TODO: Auto-generated Javadoc
/**
 * The Interface DinamicQueryDAO.
 *
 * @author Mateo
 */
public interface DinamicQueryDAO {

	/**
	 * Creates the query.
	 *
	 * @param parameter1 the parameter 1
	 * @param parameter2 the parameter 2
	 * @param filter1 the filter 1
	 * @param filter2 the filter 2
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0511> createQuery(Integer parameter1, String parameter2, String filter1, String filter2, Integer idSector);

	/**
	 * Execute query.
	 *
	 * @param query the query
	 * @return the list
	 */
	List<Pm0511> executeQuery(String query);

}
