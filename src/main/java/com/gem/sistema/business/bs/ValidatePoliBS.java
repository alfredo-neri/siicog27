package com.gem.sistema.business.bs;

/**
 * @author Mateo
 *
 */
public interface ValidatePoliBS {

	/**
	 * @param mes
	 * @param anio
	 * @param idSector
	 */
	Boolean isOpenMonth(Integer mes, Integer anio, Integer idSector);

	/**
	 * @param mes
	 * @param anio
	 * @param idSector
	 */
	Boolean isAfectMonth(Integer mes, Integer anio, Integer idSector);
	
	/**
	 * @param mes
	 * @param anio
	 * @param idSector
	 */
	Boolean existPolices(Integer mes, Integer anio, Integer idSector);

}
