package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm2711;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2711BS.
 *
 * @author Mateo
 */
public interface Pm2711BS {
	
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
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2711> orderByTrimestreAsc(Integer idSector);
	
	/**
	 * Oder by.
	 *
	 * @return the sort
	 */
	Sort oderBy();
	
	/**
	 * Find by trimestre.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @return true, if successful
	 */
	boolean findByTrimestre(Integer trimestre, Integer idSector);
	
	
	/**
	 * Validate txt.
	 *
	 * @param PM2711 the pm2711
	 * @return true, if successful
	 */
	boolean validateTxt(Pm2711 PM2711);
	
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
	List<Pm2711> acumulado(Integer idSector);
	
}
