package com.gem.sistema.business.dao;


import java.io.InputStream;
import java.util.Map;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.dto.ReportsDTO;

import net.sf.jasperreports.engine.JasperPrint;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReportsDAO.
 */
public interface ReportsDAO {
	
	/**
	 * Execute report.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @param firmas the firmas
	 * @return the reports DTO
	 */
	ReportsDTO executeReport(Integer mes, Integer idSector, Integer firmas);
	
	/**
	 * Gets the path.
	 *
	 * @param cvePath the cve path
	 * @return the path
	 */
	String getPath(String cvePath);
	
	
	/**
	 * Generate PDF.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the jasper print
	 */
	JasperPrint generatePDF(Integer mes,Integer idSector);
	
	/**
	 * Generate query.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the string
	 */
	String generateQuery(Integer mes, Integer idSector);
	
	/**
	 * Gets the parameters.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the parameters
	 */
	Map<String, Object> getParameters(Integer mes,Integer idSector);
	
	/**
	 * Creates the report pdfto fs.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @param pathFileName the path file name
	 */
	void createReportPdftoFs(Integer mes, Integer idSector, String pathFileName);
	
	/**
	 * Gets the internal report.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the internal report
	 */
	InputStream getInternalReport(Integer mes, Integer idSector);
	
	/**
	 * Gets the query totales.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the query totales
	 */
	String getQueryTotales(Integer mes, Integer idSector);

}
