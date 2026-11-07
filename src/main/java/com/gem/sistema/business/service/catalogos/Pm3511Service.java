package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm3511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3511Service.
 */
public interface Pm3511Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm3511 the list pm 3511
	 * @return the list
	 */
	List<Pm3511> save(Integer index, List<Pm3511> listPm3511);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm3511 the list pm 3511
	 * @return the list
	 */
	List<Pm3511> delete(Integer index, List<Pm3511> listPm3511);
	
	/**
	 * Clean.
	 *
	 * @return the pm 3511
	 */
	Pm3511 clean();
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm3511 the list pm 3511
	 * @return the list
	 */
	List<Pm3511> cancel(Integer index, List<Pm3511> listPm3511);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3511> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 3511
	 */
	Pm3511 add();
}
