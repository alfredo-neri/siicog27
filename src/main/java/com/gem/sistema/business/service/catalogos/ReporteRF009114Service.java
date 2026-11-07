package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dto.ReportsParamDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReporteRF009114Service.
 */
public interface ReporteRF009114Service {
	
	/**
	 * Execute procedure.
	 *
	 * @param reportDTO the report DTO
	 * @return the reports param DTO
	 */
	String  executeQuery(Integer mes, Integer idSecor, Integer niveles) ;

	ReportsParamDTO executeProcedure(ReportsParamDTO reportDTO, Integer tipo);
	
	
}
