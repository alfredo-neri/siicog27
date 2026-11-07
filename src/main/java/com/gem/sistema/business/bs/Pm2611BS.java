package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm2611;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2611BS.
 */
public interface Pm2611BS {
	
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
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2611> orderByTrimestreAsc(Integer idSector);
	
	/**
	 * Oder by.
	 *
	 * @return the sort
	 */
	Sort oderBy();
	
	/**
	 * Find by semestre.
	 *
	 * @param semestre the semestre
	 * @param idSector the id sector
	 * @return true, if successful
	 */
	boolean findBySemestre(Integer semestre, Integer idSector);
	
	
	/**
	 * Validate txt.
	 *
	 * @param pm2611 the pm 2611
	 * @return true, if successful
	 */
	boolean validateTxt(Pm2611 pm2611);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2611
	 */
	Pm2611 add();

}
