package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Pm2711;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2711Service.
 */
public interface Pm2711Service {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm2711 the list pm 2711
	 * @return the list
	 */
	List<Pm2711> save(Integer index, List<Pm2711> listPm2711);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm2711 the list pm 2711
	 * @return the list
	 */
	List<Pm2711> delete(Integer index, List<Pm2711> listPm2711);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm2711 the list pm 2711
	 * @return the list
	 */
	List<Pm2711> clean(Integer index, List<Pm2711> listPm2711);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm2711 the list pm 2711
	 * @return the list
	 */
	List<Pm2711> cancel(Integer index, List<Pm2711> listPm2711);
	
	/**
	 * Order by semestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2711> orderBySemestreAsc(Integer idSector);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2711
	 */
	Pm2711 add();
	
	/**
	 * Acumulado.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2711> acumulado(Integer idSector) ;
}
