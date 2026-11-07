package com.gem.sistema.business.dao;

import java.util.List;
import java.util.Map;

import com.gem.sistema.business.dto.ParametersRF001027DTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReporteRF001027DAO.
 *
 * @author buser
 */
public interface ReporteRF001027DAO {
	
	/**
	 * Generate query.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the string
	 */
	String generateQuery(Integer mes, Integer idSector);
	
	/**
	 * Execute totals.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the string
	 */
	String executeTotals(Integer mes, Integer idSector);
	
	/**
	 * Gets the totals.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the totals
	 */
	ParametersRF001027DTO getTotals(Integer mes, Integer idSector);
	
	/**
	 * Gets the parameters.
	 *
	 * @param mes the mes
	 * @return the parameters
	 */
	Map<String, Object> getParameters(Integer mes);

}
