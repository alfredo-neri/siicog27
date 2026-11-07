package com.gem.sistema.business.service.reportador;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReporteadorDirecto.
 */
public interface ReporteadorDirecto {
	
	/** The Constant CSV. */
	public static final int CSV = 3;
	
	/** The Constant TXT. */
	public static final int TXT = 4;
	
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
	
	/** The Constant QUOTE_TXT. */
	public static final String QUOTE_TXT = "|\"%1$s\"";
	
	/** The Constant QUOTE_CSV. */
	public static final String QUOTE_CSV = ",\"%1$s\"";
	
	/** The Constant ENCODING_UTF8. */
	public static final Charset ENCODING_UTF8 = Charset.forName("UTF-8");
	
	

	//List<String> ejecutaQueryNativo(TcReporte tcReporte);
	
	//File saveTxtReport(List<String> rowsToFile, String rutaArchivo, String nombreCompuesto) throws IOException;
	
	
	/**
	 * Gets the file report.
	 *
	 * @param tcReporte the tc reporte
	 * @param params the params
	 * @param nombreCompuesto the nombre compuesto
	 * @param typeFile the type file
	 * @return the file report
	 */
	public StreamedContent getFileReport(TcReporte tcReporte, Map<String, Object> params, String nombreCompuesto, int typeFile)  ;
	
	/**
	 * Ejecuta query nativo to file.
	 *
	 * @param tcReporte the tc reporte
	 * @param typeFile the type file
	 * @param params the params
	 * @return the string
	 */
	public String ejecutaQueryNativoToFile(TcReporte tcReporte, int typeFile, Map<String, Object> params);


}
