package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm2611;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2611Service.
 *
 * @author Mateo
 */

public interface Pm2611Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm2611 the list pm 2611
	 * @return the list
	 */
	List<Pm2611> save(Integer index, List<Pm2611> listPm2611);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm2611 the list pm 2611
	 * @return the list
	 */
	List<Pm2611> delete(Integer index, List<Pm2611> listPm2611);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm2611 the list pm 2611
	 * @return the list
	 */
	List<Pm2611> clean(Integer index, List<Pm2611> listPm2611);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm2611 the list pm 2611
	 * @return the list
	 */
	List<Pm2611> cancel(Integer index, List<Pm2611> listPm2611);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2611> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2611
	 */
	Pm2611 add();

}
