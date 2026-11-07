package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Natgas;

// TODO: Auto-generated Javadoc
/**
 * The Interface NatureExpenditureService.
 *
 * @author 
 */
public interface NatureExpenditureService {

	/**
	 * Checks if is valid previous levels.
	 *
	 * @param newRow the new row
	 * @param errorMessage the error message
	 * @return true, if is valid previous levels
	 */
	boolean isValidPreviousLevels(Natgas newRow, StringBuilder errorMessage);
	
	/**
	 * Checks if is valid previous level.
	 *
	 * @param catalgo the catalgo
	 * @param errorMsg the error msg
	 * @return true, if is valid previous level
	 */
	boolean isValidPreviousLevel(Natgas catalgo, StringBuilder errorMsg);
	
	
}
