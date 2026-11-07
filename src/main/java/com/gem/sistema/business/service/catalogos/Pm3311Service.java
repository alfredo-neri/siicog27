package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Pm3311;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3311Service.
 */
public interface Pm3311Service {
	
	/**
	 * Save.
	 *
	 * @param pm3311 the pm 3311
	 * @return true, if successful
	 */
	boolean save(Pm3311 pm3311);

	/**
	 * Delete.
	 *
	 * @param pm3311 the pm 3311
	 */
	void delete(Pm3311 pm3311);

	/**
	 * Clean.
	 *
	 * @param pm3311 the pm 3311
	 * @return the pm 3311
	 */
	Pm3311 clean(Pm3311 pm3311);

	/**
	 * Cancel.
	 *
	 * @param pm3311 the pm 3311
	 */
	void cancel(Pm3311 pm3311);

}
