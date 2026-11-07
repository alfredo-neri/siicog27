package com.gem.sistema.business.service.catalogos;



import java.io.InputStream;
import java.util.Map;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.dto.ReportsDTO;

import net.sf.jasperreports.engine.JasperPrint;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReportsService.
 */
public interface ReportsService {
	
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
	JasperPrint generatePDF(Integer mes, Integer idSector);
	
	/**
	 * Creates the report pdfto fs.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @param pathFile the path file
	 */
	void createReportPdftoFs(Integer mes, Integer idSector, String pathFile);
	
	/**
	 * Gets the internal report.
	 *
	 * @param params the params
	 * @param jasperReportName the jasper report name
	 * @return the internal report
	 */
	InputStream getInternalReport(Map<String, Object> params, String jasperReportName);
	
	
	

}
