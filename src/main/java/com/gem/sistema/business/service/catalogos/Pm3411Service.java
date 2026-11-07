package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm3411;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3411Service.
 */
public interface Pm3411Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm3411 the list pm 3411
	 * @return the list
	 */
	List<Pm3411> save(Integer index, List<Pm3411> listPm3411);

	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm3411 the list pm 3411
	 * @return the list
	 */
	List<Pm3411> delete(Integer index, List<Pm3411> listPm3411);

	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm3411 the list pm 3411
	 * @return the list
	 */
	List<Pm3411> clean(Integer index, List<Pm3411> listPm3411);

	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm3411 the list pm 3411
	 * @return the list
	 */
	List<Pm3411> cancel(Integer index, List<Pm3411> listPm3411);

	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3411> orderBySemestreAsc(Integer idSector);

	/**
	 * Adds the.
	 *
	 * @return the pm 3411
	 */
	Pm3411 add();

	/**
	 * Gets the acumulado.
	 *
	 * @param idSector the id sector
	 * @return the acumulado
	 */
	List<Pm3411> getAcumulado(Integer idSector);
}
