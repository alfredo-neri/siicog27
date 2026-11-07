package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm5311;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5311BS.
 *
 * @author Alfredo
 */
public interface Pm5311BS {

	/**
	 * Save.
	 *
	 * @param pm5311 the pm 5311
	 * @return true, if successful
	 */
	boolean save(Pm5311 pm5311);

	/**
	 * Validate txt.
	 *
	 * @param pm5311 the pm 5311
	 * @return true, if successful
	 */
	boolean validateTxt(Pm5311 pm5311);
	
	/**
	 * Count by mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return true, if successful
	 */
	boolean countByMes(Integer idSector, Integer mes);
}
