package com.gem.sistema.business.service.reportador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.gem.sistema.business.domain.TcReporte;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReportadorTXTService.
 */
public interface ReportadorTXTService {

	/**
	 * Ejecuta query nativo.
	 *
	 * @param tcReporte the tc reporte
	 * @return the list
	 */
	List<String> ejecutaQueryNativo(TcReporte tcReporte);
	
	/**
	 * Save txt report.
	 *
	 * @param rowsToFile the rows to file
	 * @param rutaArchivo the ruta archivo
	 * @param nombreCompuesto the nombre compuesto
	 * @return the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	File saveTxtReport(List<String> rowsToFile, String rutaArchivo, String nombreCompuesto) throws IOException;
	
	/**
	 * Ejecuta query nativo.
	 *
	 * @param tcReporte the tc reporte
	 * @param rutaArchivo the ruta archivo
	 * @param nombreCompuesto the nombre compuesto
	 * @return the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	File ejecutaQueryNativo(TcReporte tcReporte, String rutaArchivo, String nombreCompuesto) throws IOException;
}
