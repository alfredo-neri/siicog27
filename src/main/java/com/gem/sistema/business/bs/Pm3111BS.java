package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm3111;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3111BS.
 *
 * @author Vlad
 */
public interface Pm3111BS {
 
 /**
  * Save.
  *
  * @param pm3111 the pm 3111
  * @return true, if successful
  */
	boolean save(Pm3111 pm3111);

	/**
	 * Delete.
	 *
	 * @param pm3111 the pm 3111
	 */
	void delete(Pm3111 pm3111);

	/**
	 * Clean.
	 *
	 * @param pm3111 the pm 3111
	 * @return the pm 3111
	 */
	Pm3111 clean(Pm3111 pm3111);

	/**
	 * Cancel.
	 *
	 * @param pm3111 the pm 3111
	 */
	void cancel(Pm3111 pm3111);

	/**
	 * Validate txt.
	 *
	 * @param pm3111 the pm 3111
	 * @return true, if successful
	 */
	boolean validateTxt(Pm3111 pm3111);

	/**
	 * Find by anual.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return true, if successful
	 */
	boolean findByAnual(Integer idSector, Integer anual);
}
