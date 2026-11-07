package com.gem.sistema.business.service.catalogos;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ParametroService.
 *
 * @author Gerardo CUERO
 */
public interface ParametroService {

	
	/**
	 * Gets the values list.
	 *
	 * @param parmName the parm name
	 * @param separator the separator
	 * @return the values list
	 */
	List<String> getValuesList(String parmName, String separator);
	
	/**
	 * Gets the values list.
	 *
	 * @param parmName the parm name
	 * @return the values list
	 */
	List<String> getValuesList(String parmName);
	
	/**
	 * Checks if is in values.
	 *
	 * @param parmName the parm name
	 * @param separator the separator
	 * @param value the value
	 * @return true, if is in values
	 */
	boolean isInValues(String parmName, String separator, String value);
	
	/**
	 * Checks if is in values.
	 *
	 * @param parmName the parm name
	 * @param value the value
	 * @return true, if is in values
	 */
	boolean isInValues(String parmName, String value);
	
}
