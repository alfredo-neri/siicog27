package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm5811;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5811BS.
 *
 * @author Mateo
 */
public interface Pm5811BS {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm5811 the list pm 5811
	 * @return the list
	 */
	List<Pm5811> save(Integer index, List<Pm5811> listPm5811);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm5811 the list pm 5811
	 * @return the list
	 */
	List<Pm5811> delete(Integer index, List<Pm5811> listPm5811);
	
	/**
	 * Clean.
	 *
	 * @return the pm 5811
	 */
	Pm5811 clean();
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm5811 the list pm 5811
	 * @return the list
	 */
	List<Pm5811> cancel(Integer index, List<Pm5811> listPm5811);
	
	/**
	 * Order by semestral asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5811> orderBySemestralAsc(Integer idSector);
	
	/**
	 * Oder by.
	 *
	 * @return the sort
	 */
	Sort oderBy();
	
	/**
	 * Find by semestral.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @return true, if successful
	 */
	boolean findBySemestral(Integer trimestre, Integer idSector);
	
	
	/**
	 * Validate txt.
	 *
	 * @param PM5811 the pm5811
	 * @return true, if successful
	 */
	boolean validateTxt(Pm5811 PM5811);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 5811
	 */
	Pm5811 add();
	
}
