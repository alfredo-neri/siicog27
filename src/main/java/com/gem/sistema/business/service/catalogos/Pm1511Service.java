package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm1511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1511Service.
 */
public interface Pm1511Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm1511 the list pm 1511
	 * @return the list
	 */
	List<Pm1511> save(Integer index, List<Pm1511> listPm1511);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm1511 the list pm 1511
	 * @return the list
	 */
	List<Pm1511> delete(Integer index, List<Pm1511> listPm1511);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm1511 the list pm 1511
	 * @return the list
	 */
	List<Pm1511> clean(Integer index, List<Pm1511> listPm1511);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm1511 the list pm 1511
	 * @return the list
	 */
	List<Pm1511> cancel(Integer index, List<Pm1511> listPm1511);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm1511> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 1511
	 */
	Pm1511 add();
}
