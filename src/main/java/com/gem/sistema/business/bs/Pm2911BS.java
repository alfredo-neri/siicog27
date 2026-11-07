package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm2911;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2911BS.
 *
 * @author Mateo
 */
public interface Pm2911BS {
	
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
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2911> orderByTrimestreAsc(Integer idSector);
	
	/**
	 * Oder by.
	 *
	 * @return the sort
	 */
	Sort oderBy();
	
	/**
	 * Find by trimestre.
	 *
	 * @param semestre the semestre
	 * @param idSector the id sector
	 * @return true, if successful
	 */
	boolean findByTrimestre(Integer semestre, Integer idSector);
	
	
	/**
	 * Validate txt.
	 *
	 * @param pm2911 the pm 2911
	 * @return true, if successful
	 */
	boolean validateTxt(Pm2911 pm2911);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 2911
	 */
	Pm2911 add();
}
