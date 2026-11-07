package com.gem.sistema.business.dao;

import com.gem.sistema.business.dto.ReportsParamDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReporteRF009114DAO.
 */
public interface ReporteRF009114DAO {

	/**
	 * Execute procedure.
	 *
	 * @param reportsParamDTO
	 *            the reports param DTO
	 * @return the reports param DTO
	 */
	String  executeQuery(Integer mes, Integer idSecor, Integer niveles) ;
	ReportsParamDTO executeProcedure(ReportsParamDTO reportsParamDTO, Integer tipo);

	/**
	 * Last day.
	 *
	 * @param anio
	 *            the anio
	 * @param mes
	 *            the mes
	 * @return the integer
	 */
	Integer lastDay(Integer anio, Integer mes);

}
