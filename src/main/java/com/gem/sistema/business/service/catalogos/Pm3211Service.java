package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Pm3211;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3211Service.
 */
public interface Pm3211Service {
	
	/**
	 * Save.
	 *
	 * @param pm3211 the pm 3211
	 * @return true, if successful
	 */
	boolean save(Pm3211 pm3211);

	/**
	 * Delete.
	 *
	 * @param pm3211 the pm 3211
	 */
	void delete(Pm3211 pm3211);

	/**
	 * Clean.
	 *
	 * @param pm3211 the pm 3211
	 * @return the pm 3211
	 */
	Pm3211 clean(Pm3211 pm3211);

	/**
	 * Cancel.
	 *
	 * @param pm3211 the pm 3211
	 */
	void cancel(Pm3211 pm3211);

}
