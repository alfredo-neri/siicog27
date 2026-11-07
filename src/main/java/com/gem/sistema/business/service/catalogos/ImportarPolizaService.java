package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.PolizaImportDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ImportarPolizaService.
 */
public interface ImportarPolizaService {
	
	/**
	 * Execuet.
	 *
	 * @param polizaDTO the poliza DTO
	 * @param idSector the id sector
	 * @param idUser the id user
	 * @return the poliza import DTO
	 */
	public PolizaImportDTO execuet(PolizaImportDTO polizaDTO, Integer idSector, String idUser);
	
	/**
	 * Expor report.
	 *
	 * @param polizaDTO the poliza DTO
	 * @param tipo the tipo
	 * @return the poliza import DTO
	 */
	public PolizaImportDTO exporReport(PolizaImportDTO polizaDTO, String tipo);
	
	/**
	 * Genarte excel.
	 *
	 * @param polizaDTO the poliza DTO
	 * @return the poliza import DTO
	 */
	public PolizaImportDTO genarteExcel(PolizaImportDTO polizaDTO);
	
	/**
	 * Gets the mes activo.
	 *
	 * @param idSector the id sector
	 * @return the mes activo
	 */
	public List<String> getMesActivo(Integer idSector);

}
