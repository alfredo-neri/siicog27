package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.domain.PolizaBody;
import com.gem.sistema.business.dto.PolizaExcelDTO;
import com.gem.sistema.business.dto.PolizaImportDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ImportarPolizaDTO.
 */
public interface ImportarPolizaDTO {
	
	/**
	 * Execute.
	 *
	 * @param importDTO the import DTO
	 * @param idSector the id sector
	 * @param idUser the id user
	 * @return the poliza import DTO
	 */
	PolizaImportDTO execute(PolizaImportDTO importDTO, Integer idSector, String idUser);
	
	/**
	 * Export reports.
	 *
	 * @param importDTO the import DTO
	 * @param tipo the tipo
	 * @return the poliza import DTO
	 */
	PolizaImportDTO exportReports(PolizaImportDTO importDTO, String tipo);
	
	
	/**
	 * Generate excel.
	 *
	 * @param importDTO the import DTO
	 * @return the poliza import DTO
	 */
	PolizaImportDTO generateExcel(PolizaImportDTO importDTO);
	
	/**
	 * Generate head.
	 *
	 * @param importDTO the import DTO
	 * @return the list
	 */
	List<PolizaExcelDTO> generateHead(PolizaImportDTO importDTO);
	
	/**
	 * Generate body.
	 *
	 * @param importDTO the import DTO
	 * @return the list
	 */
	List<PolizaBody> generateBody(PolizaImportDTO importDTO);
	
	/**
	 * Gets the path.
	 *
	 * @param cvePath the cve path
	 * @return the path
	 */
	String getPath(String cvePath);
	
	/**
	 * Gets the mes activo.
	 *
	 * @param idSector the id sector
	 * @return the mes activo
	 */
	List<String> getMesActivo(Integer idSector);

}
