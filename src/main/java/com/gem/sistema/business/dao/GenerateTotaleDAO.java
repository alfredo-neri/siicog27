package com.gem.sistema.business.dao;

import com.gem.sistema.business.dto.TotalMesesDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface GenerateTotaleDAO.
 */
public interface GenerateTotaleDAO {
	
	/**
	 * Generate totales.
	 *
	 * @param idSector the id sector
	 * @param dependecia the dependecia
	 * @param programa the programa
	 * @return the total meses DTO
	 */
	TotalMesesDTO generateTotales( Integer idSector, String dependecia, String programa);
	
	
	/**
	 * Generate totales.
	 *
	 * @param idSector the id sector
	 * @param programa the programa
	 * @return the total meses DTO
	 */
	TotalMesesDTO generateTotales( Integer idSector, String programa);

}
