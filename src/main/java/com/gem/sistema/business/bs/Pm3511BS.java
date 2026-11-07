package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm3511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3511BS.
 *
 * @author Mateo
 */
public interface Pm3511BS {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm3511 the list pm 3511
	 * @return the list
	 */
	List<Pm3511> save(Integer index, List<Pm3511> listPm3511);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm3511 the list pm 3511
	 * @return the list
	 */
	List<Pm3511> delete(Integer index, List<Pm3511> listPm3511);
	
	/**
	 * Clean.
	 *
	 * @return the pm 3511
	 */
	Pm3511 clean();
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm3511 the list pm 3511
	 * @return the list
	 */
	List<Pm3511> cancel(Integer index, List<Pm3511> listPm3511);
	
	/**
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3511> orderByTrimestreAsc(Integer idSector);
	
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
	 * @param PM3511 the pm3511
	 * @return true, if successful
	 */
	boolean validateTxt(Pm3511 PM3511);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 3511
	 */
	Pm3511 add();
	
}
