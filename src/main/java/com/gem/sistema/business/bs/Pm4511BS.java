package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm4511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4511BS.
 *
 * @author Alfredo
 */
public interface Pm4511BS {
 
 /**
  * Save.
  *
  * @param pm4511 the pm 4511
  * @return true, if successful
  */
	boolean save(Pm4511 pm4511);

	/**
	 * Delete.
	 *
	 * @param pm4511 the pm 4511
	 */
	void delete(Pm4511 pm4511);

	/**
	 * Clean.
	 *
	 * @param pm4511 the pm 4511
	 * @return the pm 4511
	 */
	Pm4511 clean(Pm4511 pm4511);

	/**
	 * Cancel.
	 *
	 * @param pm4511 the pm 4511
	 */
	void cancel(Pm4511 pm4511);

	/**
	 * Validate txt.
	 *
	 * @param pm4511 the pm 4511
	 * @return true, if successful
	 */
	boolean validateTxt(Pm4511 pm4511);

	/**
	 * Find by anual.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return true, if successful
	 */
	boolean findByAnual(Integer idSector, Integer anual);
}
