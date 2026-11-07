package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm4111;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4111BS.
 *
 * @author Alfredo
 */
public interface Pm4111BS {
 
 /**
  * Save.
  *
  * @param pm4111 the pm 4111
  * @return true, if successful
  */
	boolean save(Pm4111 pm4111);

	/**
	 * Delete.
	 *
	 * @param pm4111 the pm 4111
	 */
	void delete(Pm4111 pm4111);

	/**
	 * Clean.
	 *
	 * @param pm4111 the pm 4111
	 * @return the pm 4111
	 */
	Pm4111 clean(Pm4111 pm4111);

	/**
	 * Cancel.
	 *
	 * @param pm4111 the pm 4111
	 */
	void cancel(Pm4111 pm4111);

	/**
	 * Validate txt.
	 *
	 * @param pm4111 the pm 4111
	 * @return true, if successful
	 */
	boolean validateTxt(Pm4111 pm4111);

	/**
	 * Find by anual.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return true, if successful
	 */
	boolean findByAnual(Integer idSector, Integer anual);
}
