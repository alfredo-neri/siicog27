package com.gem.sistema.business.bs;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Pm0511;

// TODO: Auto-generated Javadoc
/**
 * The Interface PM0511BS.
 *
 * @author Mateo
 */
public interface PM0511BS {
	
	/**
	 * Adds the.
	 *
	 * @return the pm 0511
	 */
	Pm0511 add();
	
	/**
	 * Save.
	 *
	 * @param pm0511 the pm 0511
	 * @return the list
	 */
	List<Pm0511> save(Pm0511 pm0511);
	
	/**
	 * Modify.
	 *
	 * @param pm0511 the pm 0511
	 * @return the list
	 */
	Pm0511 modify(Pm0511 pm0511, Integer oldValue);
	
	/**
	 * Delete.
	 *
	 * @param pm0511 the pm 0511
	 * @return the list
	 */
	List<Pm0511> delete(Pm0511 pm0511);
	
	/**
	 * Order by asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0511> orderByAsc(Integer idSector);
	
	/**
	 * Gets the sort.
	 *
	 * @return the sort
	 */
	Sort getSort();
	
	/**
	 * Validate txt.
	 *
	 * @param pm0511 the pm 0511
	 * @return the boolean
	 */
	Boolean validateTxt(Pm0511 pm0511);
	
	/**
	 * Exist trimonth.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @param conse the conse
	 * @return the boolean
	 */
	Boolean existTrimonth(Integer trimestre, Integer idSector, String conse);
	
	/**
	 * Gets the tri month.
	 *
	 * @return the tri month
	 */
	List<String> getTriMonth();

}
