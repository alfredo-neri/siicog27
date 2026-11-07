package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm0811;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0811Service.
 */
public interface Pm0811Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm0811 the list pm 0811
	 * @return the list
	 */
	List<Pm0811> save(Integer index, List<Pm0811> listPm0811);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm0811 the list pm 0811
	 * @return the list
	 */
	List<Pm0811> delete(Integer index, List<Pm0811> listPm0811);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm0811 the list pm 0811
	 * @return the list
	 */
	List<Pm0811> clean(Integer index, List<Pm0811> listPm0811);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm0811 the list pm 0811
	 * @return the list
	 */
	List<Pm0811> cancel(Integer index, List<Pm0811> listPm0811);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0811> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 0811
	 */
	Pm0811 add();
}
