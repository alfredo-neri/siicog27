package com.gem.sistema.business.bs;

import com.gem.sistema.business.domain.Pm4211;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4211BS.
 *
 * @author Alfredo
 */
public interface Pm4211BS {

	/**
	 * Save.
	 *
	 * @param pm4211 the pm 4211
	 * @return true, if successful
	 */
	boolean save(Pm4211 pm4211);

	/**
	 * Validate txt.
	 *
	 * @param pm4211 the pm 4211
	 * @return true, if successful
	 */
	boolean validateTxt(Pm4211 pm4211);
	
	/**
	 * Count by mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return true, if successful
	 */
	boolean countByMes(Integer idSector, Integer mes);
}
