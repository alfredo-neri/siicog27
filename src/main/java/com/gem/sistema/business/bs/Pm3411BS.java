package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm3411;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3411BS.
 *
 * @author Mateo
 */
public interface Pm3411BS {

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
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3411> orderByTrimestreAsc(Integer idSector);

	/**
	 * Oder by.
	 *
	 * @return the sort
	 */
	Sort oderBy();

	/**
	 * Find by trimestre.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @return true, if successful
	 */
	boolean findByTrimestre(Integer trimestre, Integer idSector);

	/**
	 * Validate txt.
	 *
	 * @param PM3411 the pm3411
	 * @return true, if successful
	 */
	boolean validateTxt(Pm3411 PM3411);

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
