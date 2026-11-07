package com.gem.sistema.business.service.reportador;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Interface JasperGenericService.
 *
 * @author Gerardo CUERO
 */
public interface JasperGenericService {
	
	/** The Constant PDF. */
	public static final int PDF = 1;
	
	/** The Constant XLS. */
	public static final int XLS = 2;
	
	/** The Constant CSV. */
	public static final int CSV = 3;
	
	/** The Constant TXT. */
	public static final int TXT = 4;

	/**
	 * Extension de un archivo Excel.
	 */
	public static final String XLS_EXTENSION = ".xls";

	/**
	 * Extension de un archivo PDF.
	 */
	public static final String PDF_EXTENSION = ".pdf";

	/**
	 * Tipo de contenido web pdf.
	 */
	public static final String CONTENT_TYPE_PDF = "application/pdf";

	/**
	 * Tipo de contenido web excel.
	 */
	public static final String CONTENT_TYPE_XLS = "application/vnd.ms-excel";

	
	/** The Constant TYPE_CONTENT_TEXT_PLAIN. */
	public static final String TYPE_CONTENT_TEXT_PLAIN= FrontProperty.getPropertyValue("type.content.text.plain");
	
	/** The Constant TYPE_CONTENT_CSV. */
	public static final String TYPE_CONTENT_CSV= FrontProperty.getPropertyValue("type.content.csv");

	/** Extension de los archivos de texto plano.
	 */
	public static final String TXT_EXTENSION = ".txt";
	
	/** Extension de los archivos de texto plano.
	 */
	public static final String CSV_EXTENSION = ".csv";
	
	/**
	 * Gets the internal report.
	 *
	 * @param params the params
	 * @param jasperReportName the jasper report name
	 * @param type the type
	 * @return the internal report
	 */
	InputStream getInternalReport(Map<String, Object> params, String jasperReportName,  int type);
	
	/**
	 * Gets the report.
	 *
	 * @param params the params
	 * @param jasperReportName the jasper report name
	 * @param type the type
	 * @return the report
	 */
	StreamedContent getReport(Map<String, Object> params, String jasperReportName, String endFileName, int type);
	
	/**
	 * Creates the report pdfto fs.
	 *
	 * @param params the params
	 * @param jasperReportName the jasper report name
	 * @param pathFileName the path file name
	 */
	void createReportPdftoFs(Map<String, Object> params, String jasperReportName, String pathFileName);
	
	void createReportPdftoFs(Map<String, Object> params, String jasperReportName, String pathFileName, File jsonFile);
}
