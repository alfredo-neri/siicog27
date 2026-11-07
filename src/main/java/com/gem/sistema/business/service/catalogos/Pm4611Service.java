package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Pm4611;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4611Service.
 */
public interface Pm4611Service {
	
	/**
	 * Save.
	 *
	 * @param pm4611 the pm 4611
	 * @return true, if successful
	 */
	boolean save(Pm4611 pm4611);

	/**
	 * Delete.
	 *
	 * @param pm4611 the pm 4611
	 */
	void delete(Pm4611 pm4611);

	/**
	 * Clean.
	 *
	 * @param pm4611 the pm 4611
	 * @return the pm 4611
	 */
	Pm4611 clean(Pm4611 pm4611);

	/**
	 * Cancel.
	 *
	 * @param pm4611 the pm 4611
	 */
	void cancel(Pm4611 pm4611);

}
