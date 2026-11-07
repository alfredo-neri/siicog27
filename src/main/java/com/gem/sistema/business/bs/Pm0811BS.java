package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm0811;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0811BS.
 *
 * @author Mateo
 */
public interface Pm0811BS {
	
	/**
	 * Save.
	 *
	 * @param index the index
	 * @param listPm0811 the list pm 0811
	 * @return the list
	 */
	List<Pm0811> save(Integer index, List<Pm0811> listPm0811);
	
	/**
	 * Delete.
	 *
	 * @param index the index
	 * @param listPm0811 the list pm 0811
	 * @return the list
	 */
	List<Pm0811> delete(Integer index, List<Pm0811> listPm0811);
	
	/**
	 * Clean.
	 *
	 * @param index the index
	 * @param listPm0811 the list pm 0811
	 * @return the list
	 */
	List<Pm0811> clean(Integer index, List<Pm0811> listPm0811);
	
	/**
	 * Cancel.
	 *
	 * @param index the index
	 * @param listPm0811 the list pm 0811
	 * @return the list
	 */
	List<Pm0811> cancel(Integer index, List<Pm0811> listPm0811);
	
	/**
	 * Order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0811> orderByTrimestreAsc(Integer idSector);
	
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
	 * @param pm0811 the pm 0811
	 * @return true, if successful
	 */
	boolean validateTxt(Pm0811 pm0811);
	
	/**
	 * Adds the.
	 *
	 * @return the pm 0811
	 */
	Pm0811 add();
}
