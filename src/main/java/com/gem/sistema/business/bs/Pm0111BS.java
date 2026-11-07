package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm0111;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0111BS.
 *
 * @author Alfredo
 */
public interface Pm0111BS {
 
 /**
  * Save.
  *
  * @param pm0111 the pm 0111
  * @return true, if successful
  */
	boolean save(Pm0111 pm0111);

	/**
	 * Delete.
	 *
	 * @param pm0111 the pm 0111
	 */
	void delete(Pm0111 pm0111);

	/**
	 * Clean.
	 *
	 * @param pm0111 the pm 0111
	 * @return the pm 0111
	 */
	Pm0111 clean(Pm0111 pm0111);

	/**
	 * Cancel.
	 *
	 * @param pm0111 the pm 0111
	 */
	void cancel(Pm0111 pm0111);

	/**
	 * Validate txt.
	 *
	 * @param pm0111 the pm 0111
	 * @return true, if successful
	 */
	boolean validateTxt(Pm0111 pm0111);

	/**
	 * Find by anual.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return true, if successful
	 */
	boolean findByAnual(Integer idSector, Integer anual);
}
