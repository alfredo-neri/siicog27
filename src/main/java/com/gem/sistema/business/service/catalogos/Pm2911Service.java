package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm2911;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2911Service.
 */
public interface Pm2911Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm2911 the list pm 2911
	 * @return the list
	 */
	List<Pm2911> save(Integer index, List<Pm2911> listPm2911);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm2911 the list pm 2911
	 * @return the list
	 */
	List<Pm2911> delete(Integer index, List<Pm2911> listPm2911);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm2911 the list pm 2911
	 * @return the list
	 */
	List<Pm2911> clean(Integer index, List<Pm2911> listPm2911);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm2911 the list pm 2911
	 * @return the list
	 */
	List<Pm2911> cancel(Integer index, List<Pm2911> listPm2911);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2911> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2911
	 */
	Pm2911 add();
}
