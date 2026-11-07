package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Pm0711;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0711Service.
 */
public interface Pm0711Service {
	
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

}
