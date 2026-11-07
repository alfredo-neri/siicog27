/**
 * 
 */
package com.gem.sistema.load.fileupload.service;

import com.gem.sistema.load.fileupload.model.FileUpload;

// TODO: Auto-generated Javadoc
/**
 * The Interface OutboundFIleService.
 *
 * @author Cesar Ocampo
 */
public interface OutboundFIleService {

	
	/**
	 * Valida cuenta.
	 *
	 * @param cta the cta
	 * @param idsector the idsector
	 * @return true, if successful
	 */
	public boolean validaCuenta(String cta, Long idsector);
	
	/**
	 * Valida fuente F.
	 *
	 * @param ffin the ffin
	 * @return true, if successful
	 */
	public boolean validaFuenteF(String ffin);
	
	/**
	 * Validate content.
	 *
	 * @param fileUpload the file upload
	 * @param idsector the idsector
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean validateContent(FileUpload fileUpload, Long idsector) throws Exception;
	
	/**
	 * Load ingreso to table.
	 */
	public void loadIngresoToTable();
}
