package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm2311;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2311Service.
 */
public interface Pm2311Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm2311 the list pm 2311
	 * @return the list
	 */
	List<Pm2311> save(Integer index, List<Pm2311> listPm2311);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm2311 the list pm 2311
	 * @return the list
	 */
	List<Pm2311> delete(Integer index, List<Pm2311> listPm2311);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm2311 the list pm 2311
	 * @return the list
	 */
	List<Pm2311> clean(Integer index, List<Pm2311> listPm2311);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm2311 the list pm 2311
	 * @return the list
	 */
	List<Pm2311> cancel(Integer index, List<Pm2311> listPm2311);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2311> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2311
	 */
	Pm2311 add();
}
