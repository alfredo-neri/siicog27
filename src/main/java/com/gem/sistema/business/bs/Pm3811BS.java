package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm3811;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3811BS.
 *
 * @author Mateo
 */
public interface Pm3811BS {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm3811 the list pm 3811
	 * @return the list
	 */
	List<Pm3811> save(Integer index, List<Pm3811> listPm3811);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm3811 the list pm 3811
	 * @return the list
	 */
	List<Pm3811> delete(Integer index, List<Pm3811> listPm3811);
	
	/**
	 * Clean.
	 *
	 * @return the pm 3811
	 */
	Pm3811 clean();
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm3811 the list pm 3811
	 * @return the list
	 */
	List<Pm3811> cancel(Integer index, List<Pm3811> listPm3811);
	
	/**
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3811> orderByTrimestreAsc(Integer idSector);
	
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
	 * @param PM3811 the pm3811
	 * @return true, if successful
	 */
	boolean validateTxt(Pm3811 PM3811);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 3811
	 */
	Pm3811 add();
	
}
