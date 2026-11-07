package com.gem.sistema.business.bs;

import java.io.IOException;
import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConvertToTiffPdfBS.
 *
 * @author Mateo
 */
public interface ConvertToTiffPdfBS {
	
	/**
	 * Convert files to PDF.
	 *
	 * @param fileName the file name
	 * @param uuid the uuid
	 * @return the string
	 * @throws Exception the exception
	 */
	String convertFilesToPDF(String fileName, String uuid) throws Exception;
	
	/**
	 * Copy file.
	 *
	 * @param path the path
	 * @param filename the filename
	 * @param uuid the uuid
	 * @param pathTmp the path tmp
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	String copyFile(String path, String filename, String uuid, String pathTmp) throws IOException;
	
	/**
	 * Removes the file.
	 *
	 * @param fileName the file name
	 */
	void removeFile(String fileName);

}
