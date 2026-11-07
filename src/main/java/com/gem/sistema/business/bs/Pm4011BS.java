package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm4011;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4011BS.
 *
 * @author Mateo
 */
public interface Pm4011BS {

	/**
	 * Save.
	 *
	 * @param pm4011 the pm 4011
	 * @return the list
	 */
	List<Pm4011> save(Pm4011 pm4011);

	/**
	 * Delete.
	 *
	 * @param pm4011 the pm 4011
	 * @return the list
	 */
	List<Pm4011> delete(Pm4011 pm4011);

	/**
	 * Clean.
	 *
	 * @param pm4011 the pm 4011
	 * @return the list
	 */
	List<Pm4011> clean(Pm4011 pm4011);

	/**
	 * Cancel.
	 *
	 * @param pm4011 the pm 4011
	 * @return the list
	 */
	List<Pm4011> cancel(Pm4011 pm4011);

	/**
	 * Order by clave asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm4011> orderByClaveAsc(Integer idSector);

	/**
	 * Oder by.
	 *
	 * @return the sort
	 */
	Sort oderBy();

	/**
	 * Find by clave.
	 *
	 * @param clave the clave
	 * @param idSector the id sector
	 * @return true, if successful
	 */
	boolean findByClave(Integer clave, Integer idSector);

	/**
	 * Validate txt.
	 *
	 * @param pm4011 the pm 4011
	 * @return true, if successful
	 */
	boolean validateTxt(Pm4011 pm4011);

	/**
	 * Adds the.
	 *
	 * @return the pm 4011
	 */
	Pm4011 add();

	/**
	 * Load data.
	 *
	 * @param idSector the id sector
	 * @return the pm 4011
	 */
	Pm4011 loadData(Integer idSector);

	/**
	 * Update.
	 *
	 * @param pm4011 the pm 4011
	 * @return the list
	 */
	List<Pm4011> update(Pm4011 pm4011);

	/**
	 * Not empty.
	 *
	 * @param pm4011 the pm 4011
	 * @return the pm 4011
	 */
	Pm4011 notEmpty(Pm4011 pm4011);
}
