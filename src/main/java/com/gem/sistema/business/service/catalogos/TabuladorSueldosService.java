package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Tsueldo;

// TODO: Auto-generated Javadoc
/**
 * The Interface TabuladorSueldosService.
 */
public interface TabuladorSueldosService {
	
	/**
	 * Execuet.
	 *
	 * @param fileName the file name
	 * @param filePath the file path
	 * @param idUser the id user
	 * @param idSector the id sector
	 * @return the tsueldo
	 */
	public Tsueldo execuet(String fileName, String filePath, String idUser, Integer idSector); 
}
