package com.gem.sistema.business.service.catalogos;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pbrm08DetailService.
 *
 * @author Dalia Tovar
 */
public interface Pbrm08DetailService  {
	
	/**
	 * Execute query head.
	 *
	 * @param idsector the idsector
	 * @param trim the trim
	 * @return the string
	 */
	String executeQueryHead (Integer idsector, Integer trim);
	
	/**
	 * Execute query detail.
	 *
	 * @param idsector the idsector
	 * @param trim the trim
	 * @return the string
	 */
	String executeQueryDetail(Integer idsector, Integer trim);
	
	

}
