package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm3311;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3311BS.
 *
 * @author Alfredo
 */
public interface Pm3311BS {
 
 /**
  * Save.
  *
  * @param pm3311 the pm 3311
  * @return true, if successful
  */
	boolean save(Pm3311 pm3311);

	/**
	 * Delete.
	 *
	 * @param pm3311 the pm 3311
	 */
	void delete(Pm3311 pm3311);

	/**
	 * Clean.
	 *
	 * @param pm3311 the pm 3311
	 * @return the pm 3311
	 */
	Pm3311 clean(Pm3311 pm3311);

	/**
	 * Cancel.
	 *
	 * @param pm3311 the pm 3311
	 */
	void cancel(Pm3311 pm3311);

	/**
	 * Validate txt.
	 *
	 * @param pm3311 the pm 3311
	 * @return true, if successful
	 */
	boolean validateTxt(Pm3311 pm3311);

	/**
	 * Find by anual.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return true, if successful
	 */
	boolean findByAnual(Integer idSector, Integer anual);
}
