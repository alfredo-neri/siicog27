package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm2511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2511Service.
 */
public interface Pm2511Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm2511 the list pm 2511
	 * @return the list
	 */
	List<Pm2511> save(Integer index, List<Pm2511> listPm2511);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm2511 the list pm 2511
	 * @return the list
	 */
	List<Pm2511> delete(Integer index, List<Pm2511> listPm2511);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm2511 the list pm 2511
	 * @return the list
	 */
	List<Pm2511> clean(Integer index, List<Pm2511> listPm2511);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm2511 the list pm 2511
	 * @return the list
	 */
	List<Pm2511> cancel(Integer index, List<Pm2511> listPm2511);
	
	/**
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2511> orderByTrimestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2511
	 */
	Pm2511 add();
}
