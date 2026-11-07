package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm1011;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1011BS.
 *
 * @author Alfredo
 */
public interface Pm1011BS {
 
 /**
  * Save.
  *
  * @param pm1011 the pm 1011
  * @return true, if successful
  */
	boolean save(Pm1011 pm1011);

	/**
	 * Delete.
	 *
	 * @param pm1011 the pm 1011
	 */
	void delete(Pm1011 pm1011);

	/**
	 * Clean.
	 *
	 * @param pm1011 the pm 1011
	 * @return the pm 1011
	 */
	Pm1011 clean(Pm1011 pm1011);

	/**
	 * Cancel.
	 *
	 * @param pm1011 the pm 1011
	 */
	void cancel(Pm1011 pm1011);

	/**
	 * Validate txt.
	 *
	 * @param pm1011 the pm 1011
	 * @return true, if successful
	 */
	boolean validateTxt(Pm1011 pm1011);

	/**
	 * Find by anual.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return true, if successful
	 */
	boolean findByAnual(Integer idSector, Integer anual);
	
	/**
	 * @param pm1011
	 */
	void update(Pm1011 pm1011);
}
