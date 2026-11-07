package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm2411;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2411BS.
 *
 * @author Alfredo
 */
public interface Pm2411BS {
 
 /**
  * Save.
  *
  * @param pm2411 the pm 2411
  * @return true, if successful
  */
	boolean save(Pm2411 pm2411);

	/**
	 * Delete.
	 *
	 * @param pm2411 the pm 2411
	 */
	void delete(Pm2411 pm2411);

	/**
	 * Clean.
	 *
	 * @param pm2411 the pm 2411
	 * @return the pm 2411
	 */
	Pm2411 clean(Pm2411 pm2411);

	/**
	 * Cancel.
	 *
	 * @param pm2411 the pm 2411
	 */
	void cancel(Pm2411 pm2411);

	/**
	 * Validate txt.
	 *
	 * @param pm2411 the pm 2411
	 * @return true, if successful
	 */
	boolean validateTxt(Pm2411 pm2411);

	/**
	 * Find by anual.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return true, if successful
	 */
	boolean findByAnual(Integer idSector, Integer anual);
}
