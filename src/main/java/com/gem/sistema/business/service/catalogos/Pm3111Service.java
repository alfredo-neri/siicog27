package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Pm3111;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3111Service.
 */
public interface Pm3111Service {
	
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

}
