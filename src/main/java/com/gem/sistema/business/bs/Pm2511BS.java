package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm2511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2511BS.
 *
 * @author Mateo
 */
public interface Pm2511BS {
	
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
	 * @param pm2511 the pm 2511
	 * @return true, if successful
	 */
	boolean validateTxt(Pm2511 pm2511);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2511
	 */
	Pm2511 add();
}
