/**
 * 
 */
package com.gem.sistema.business.dao;

import com.gem.sistema.business.dto.ArchivoCuentas;

// TODO: Auto-generated Javadoc
/**
 * The Interface GeneraArchivoCuentasDAO.
 *
 * @author Gerardo CUERO
 */
public interface GeneraArchivoCuentasDAO {

	/**
	 * Generate file.
	 *
	 * @param header the header
	 * @param query the query
	 * @param fileName the file name
	 * @return the archivo cuentas
	 */
	ArchivoCuentas generateFile(String header, String query, String fileName);
}
