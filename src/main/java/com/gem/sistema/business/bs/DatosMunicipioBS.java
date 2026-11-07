package com.gem.sistema.business.bs;

/**
 * @author Mateo
 *
 */
public interface DatosMunicipioBS {

	/**
	 * @param clave
	 * @param idSector
	 */
	void update(String clave, Integer idSector, String userName);
	
	
	/**
	 * @param clave
	 * @return
	 */
	Long getClvMunicipio(String clave);

}
