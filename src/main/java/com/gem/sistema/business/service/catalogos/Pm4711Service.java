package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Pm4711;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4711Service.
 */
public interface Pm4711Service {
	
	/**
	 * Save.
	 *
	 * @param pm4711 the pm 4711
	 * @return true, if successful
	 */
	boolean save(Pm4711 pm4711);

	/**
	 * Delete.
	 *
	 * @param pm4711 the pm 4711
	 */
	void delete(Pm4711 pm4711);

	/**
	 * Clean.
	 *
	 * @param pm4711 the pm 4711
	 * @return the pm 4711
	 */
	Pm4711 clean(Pm4711 pm4711);

	/**
	 * Cancel.
	 *
	 * @param pm4711 the pm 4711
	 */
	void cancel(Pm4711 pm4711);

}
