package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm3911;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3911Service.
 */
public interface Pm3911Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm3911 the list pm 3911
	 * @return the list
	 */
	List<Pm3911> save(Integer index, List<Pm3911> listPm3911);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm3911 the list pm 3911
	 * @return the list
	 */
	List<Pm3911> delete(Integer index, List<Pm3911> listPm3911);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm3911 the list pm 3911
	 * @return the list
	 */
	List<Pm3911> clean(Integer index, List<Pm3911> listPm3911);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm3911 the list pm 3911
	 * @return the list
	 */
	List<Pm3911> cancel(Integer index, List<Pm3911> listPm3911);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3911> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 3911
	 */
	Pm3911 add();
}
