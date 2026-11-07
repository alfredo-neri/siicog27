package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Pm4111;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4111Service.
 */
public interface Pm4111Service {
	
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

}
