/**
 * 
 */
package com.gem.sistema.business.service.callsp;


// TODO: Auto-generated Javadoc
/**
 * The Interface CallSpService.
 */
public interface CallSpService {
	
	/**
	 * Gets the file.
	 *
	 * @param plName the pl name
	 * @param param the param
	 * @return the file
	 */
	public void getFile(String plName, ParamsSpDTO param);
	
	/**
	 * Gets the zipped file.
	 *
	 * @param baseNameZip the base name zip
	 * @param plName the pl name
	 * @param param the param
	 * @return the zipped file
	 */
	public void getZippedFile(String baseNameZip, String plName, ParamsSpDTO param);
}
