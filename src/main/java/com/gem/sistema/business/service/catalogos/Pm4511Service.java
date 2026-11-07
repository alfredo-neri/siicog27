package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Pm4511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4511Service.
 */
public interface Pm4511Service {
	
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

}
