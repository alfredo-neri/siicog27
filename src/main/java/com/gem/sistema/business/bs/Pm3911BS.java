package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm3911;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3911BS.
 *
 * @author Mateo
 */
public interface Pm3911BS {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm3911 the list pm 3911
	 * @return the list
	 */
	List<Pm3911> save(Integer index, List<Pm3911> listPm3911);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm3911 the list pm 3911
	 * @return the list
	 */
	List<Pm3911> delete(Integer index, List<Pm3911> listPm3911);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm3911 the list pm 3911
	 * @return the list
	 */
	List<Pm3911> clean(Integer index, List<Pm3911> listPm3911);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm3911 the list pm 3911
	 * @return the list
	 */
	List<Pm3911> cancel(Integer index, List<Pm3911> listPm3911);
	
	/**
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3911> orderByTrimestreAsc(Integer idSector);
	
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
	 * @param PM3911 the pm3911
	 * @return true, if successful
	 */
	boolean validateTxt(Pm3911 PM3911);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 3911
	 */
	Pm3911 add();
	
}
