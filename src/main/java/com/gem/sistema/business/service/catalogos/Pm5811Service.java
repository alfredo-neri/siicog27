package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm5811;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5811Service.
 */
public interface Pm5811Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm5811 the list pm 5811
	 * @return the list
	 */
	List<Pm5811> save(Integer index, List<Pm5811> listPm5811);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm5811 the list pm 5811
	 * @return the list
	 */
	List<Pm5811> delete(Integer index, List<Pm5811> listPm5811);
	
	/**
	 * Clean.
	 *
	 * @return the pm 5811
	 */
	Pm5811 clean();
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm5811 the list pm 5811
	 * @return the list
	 */
	List<Pm5811> cancel(Integer index, List<Pm5811> listPm5811);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5811> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 5811
	 */
	Pm5811 add();
}
