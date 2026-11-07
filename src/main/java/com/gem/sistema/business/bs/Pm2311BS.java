package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm2311;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2311BS.
 *
 * @author Mateo
 */
public interface Pm2311BS {
	
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
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2311> orderByTrimestreAsc(Integer idSector);
	
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
	 * @param PM2311 the pm2311
	 * @return true, if successful
	 */
	boolean validateTxt(Pm2311 PM2311);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2311
	 */
	Pm2311 add();
	
}
