package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Catflu;

// TODO: Auto-generated Javadoc
/**
 * The Interface FlowService.
 *
 * @author 
 */
public interface FlowService {

	/**
	 * Checks if is valid previous level.
	 *
	 * @param newRow the new row
	 * @param errorMsg the error msg
	 * @return true, if is valid previous level
	 */
	boolean isValidPreviousLevel(Catflu newRow, StringBuilder errorMsg);
	
}
