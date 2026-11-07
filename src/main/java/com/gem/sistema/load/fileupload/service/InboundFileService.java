package com.gem.sistema.load.fileupload.service;

import java.util.List;

import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.results.EgresResultBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface InboundFileService.
 *
 * @author Cesar Ocampo
 */

public interface InboundFileService {

	
	
	/**
	 * Method to validate dependencies to XCatpro.
	 *
	 * @param clvdep the clvdep
	 * @return boolean
	 */
	public boolean existDependency(String clvdep);
	
	
	
	/**
	 * Exist clv pro.
	 *
	 * @param clvpro the clvpro
	 * @param idSector the id sector
	 * @return true, if successful
	 */
	public boolean existClvPro(String clvpro, Integer idSector);
	
	/**
	 * Validates NATGAS.
	 *
	 * @param natgas the natgas
	 * @param idSector the id sector
	 * @return boolean
	 */
	public boolean existNatGas(String natgas, Integer idSector);
	
	
	/**
	 * Validate accounts.
	 *
	 * @param accounts the accounts
	 * @param scta the scta
	 * @param sscta the sscta
	 * @param ssscta the ssscta
	 * @param idsector the idsector
	 * @return true, if successful
	 */
	public boolean validateAccounts(List accounts, String scta, String sscta, String ssscta, Long idsector);
	

	/**
	 * Validate content.
	 *
	 * @param fileUpload the file upload
	 * @param processA the process A
	 * @param idSector the id sector
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean validateContent(FileUpload fileUpload, boolean processA, Integer idSector) throws Exception;
	
	
	/**
	 * Validate fuente fin.
	 *
	 * @param fuenteFin the fuente fin
	 * @return true, if successful
	 */
	public boolean validateFuenteFin(String fuenteFin);

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public List<EgresResultBean> getResults();
	
}
