package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm4411;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4411BS.
 *
 * @author Alfredo
 */
public interface Pm4411BS {

	/**
	 * Save.
	 *
	 * @param pm4411 the pm 4411
	 * @return true, if successful
	 */
	boolean save(Pm4411 pm4411);

	/**
	 * Validate txt.
	 *
	 * @param pm4411 the pm 4411
	 * @return true, if successful
	 */
	boolean validateTxt(Pm4411 pm4411);
	
	/**
	 * Count by mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return true, if successful
	 */
	boolean countByMes(Integer idSector, Integer mes);
}
