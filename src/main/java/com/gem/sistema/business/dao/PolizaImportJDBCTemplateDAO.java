package com.gem.sistema.business.dao;


import com.gem.sistema.business.dto.PolizaImportDTO;
// TODO: Auto-generated Javadoc

/**
 * The Interface PolizaImportJDBCTemplateDAO.
 */
public interface PolizaImportJDBCTemplateDAO {

	/**
	 * Carga archivos poliza.
	 *
	 * @param poliza the poliza
	 * @return the poliza import DTO
	 */
	PolizaImportDTO cargaArchivosPoliza(PolizaImportDTO poliza);
	
}
