package com.gem.sistema.business.service.catalogos;


// TODO: Auto-generated Javadoc
/**
 * The Interface ConvertToTiffPdfService.
 *
 * @author Mateo
 */
public interface ConvertToTiffPdfService {
	

	/**
	 * Convert files to PDF.
	 *
	 * @param patgFileName the patg file name
	 * @param uuid the uuid
	 * @return the string
	 * @throws Exception the exception
	 */
	String convertFilesToPDF(String patgFileName, String uuid) throws Exception;

}
