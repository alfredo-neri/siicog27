package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm1511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1511BS.
 *
 * @author Mateo
 */
public interface Pm1511BS {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm1511 the list pm 1511
	 * @return the list
	 */
	List<Pm1511> save(Integer index, List<Pm1511> listPm1511);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm1511 the list pm 1511
	 * @return the list
	 */
	List<Pm1511> delete(Integer index, List<Pm1511> listPm1511);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm1511 the list pm 1511
	 * @return the list
	 */
	List<Pm1511> clean(Integer index, List<Pm1511> listPm1511);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm1511 the list pm 1511
	 * @return the list
	 */
	List<Pm1511> cancel(Integer index, List<Pm1511> listPm1511);
	
	/**
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm1511> orderByTrimestreAsc(Integer idSector);
	
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
	 * @param pm1511 the pm 1511
	 * @return true, if successful
	 */
	boolean validateTxt(Pm1511 pm1511);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 1511
	 */
	Pm1511 add();
	
}
