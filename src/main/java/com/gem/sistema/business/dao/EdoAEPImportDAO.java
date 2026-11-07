package com.gem.sistema.business.dao;

import com.gem.sistema.business.dto.EdoAEPImportDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface EdoAEPImportDAO.
 *
 * @author Gerardo CUERO
 */
public interface EdoAEPImportDAO {

	/**
	 * Do import.
	 *
	 * @param edoAEPImportDTO the edo AEP import DTO
	 * @return the edo AEP import DTO
	 */
	EdoAEPImportDTO doImport(EdoAEPImportDTO edoAEPImportDTO);
	
	/**
	 * Do import ingreso.
	 *
	 * @param edoAEPImportDTO the edo AEP import DTO
	 * @return the edo AEP import DTO
	 */
	EdoAEPImportDTO doImportIngreso(EdoAEPImportDTO edoAEPImportDTO);
}
