package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm0711;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0711BS.
 *
 * @author Mateo
 */
public interface Pm0711BS {

	/**
	 * Save.
	 *
	 * @param pm0711 the pm 0711
	 * @return true, if successful
	 */
	boolean save(Pm0711 pm0711);

	/**
	 * Delete.
	 *
	 * @param pm0711 the pm 0711
	 */
	void delete(Pm0711 pm0711);

	/**
	 * Clean.
	 *
	 * @param pm0711 the pm 0711
	 * @return the pm 0711
	 */
	Pm0711 clean(Pm0711 pm0711);

	/**
	 * Cancel.
	 *
	 * @param pm0711 the pm 0711
	 */
	void cancel(Pm0711 pm0711);

	/**
	 * Validate txt.
	 *
	 * @param pm0711 the pm 0711
	 * @return true, if successful
	 */
	boolean validateTxt(Pm0711 pm0711);
	
	/**
	 * Find by anual.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return true, if successful
	 */
	boolean findByAnual(Integer idSector, Integer anual);
}
