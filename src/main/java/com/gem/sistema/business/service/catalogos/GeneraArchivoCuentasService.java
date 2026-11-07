package com.gem.sistema.business.service.catalogos;

import java.util.Map;

import org.primefaces.model.StreamedContent;

// TODO: Auto-generated Javadoc
/**
 * The Interface GeneraArchivoCuentasService.
 *
 * @author Gerardo CUERO
 */
public interface GeneraArchivoCuentasService {

	/**
	 * Gets the txt file.
	 *
	 * @param filters the filters
	 * @return the txt file
	 */
	StreamedContent getTxtFile(Map<String, Object> filters);

	/**
	 * Gets the csv file.
	 *
	 * @param filters the filters
	 * @return the csv file
	 */
	StreamedContent getCsvFile(Map<String, Object> filters);

	/**
	 * Count by filters.
	 *
	 * @param filters the filters
	 * @return the long
	 */
	Long countByFilters(Map<String, Object> filters);

}
