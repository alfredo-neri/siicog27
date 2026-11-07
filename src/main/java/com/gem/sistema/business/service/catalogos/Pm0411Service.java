package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm0411;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0411Service.
 */
public interface Pm0411Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm0411 the list pm 0411
	 * @return the list
	 */
	List<Pm0411> save(Integer index, List<Pm0411> listPm0411);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm0411 the list pm 0411
	 * @return the list
	 */
	List<Pm0411> delete(Integer index, List<Pm0411> listPm0411);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm0411 the list pm 0411
	 * @return the list
	 */
	List<Pm0411> clean(Integer index, List<Pm0411> listPm0411);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm0411 the list pm 0411
	 * @return the list
	 */
	List<Pm0411> cancel(Integer index, List<Pm0411> listPm0411);
	
	/**
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0411> orderByTrimestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 0411
	 */
	Pm0411 add();
	
	/**
	 * Calculation accumulated.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0411> calculationAccumulated(Integer idSector);

}
