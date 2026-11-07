package com.gem.sistema.business.dao;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pbrm08DetailDAO.
 *
 * @author Dalia Tovar
 */

public interface Pbrm08DetailDAO {
	
	/**
	 * Execute query head.
	 *
	 * @param idsector the idsector
	 * @param trim the trim
	 * @return the string
	 */
	String executeQueryHead (Integer idsector, Integer trim);
	
	/**
	 * Execute query group.
	 *
	 * @param idsector the idsector
	 * @param trim the trim
	 * @return the string
	 */
	String executeQueryGroup(Integer idsector, Integer trim);
	
	/**
	 * Execute query detail.
	 *
	 * @param idsector the idsector
	 * @param trim the trim
	 * @return the string
	 */
	String executeQueryDetail(Integer idsector, Integer trim);
	
	/**
	 * Execute query.
	 *
	 * @param idsector the idsector
	 * @param trim the trim
	 * @return the string
	 */
	String executeQuery(Integer idsector, Integer trim);
	
	

}
