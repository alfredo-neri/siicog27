package com.gem.sistema.business.service.reportador;

/**
 * @author Alfredo Neri
 *
 */
public interface GeneraTxtFilesService {

	/**
	 * Estado Análitico del ejercicio del Presupuesto de Egresos Detallado - LDF
	 * Clasificación por Objeto del Gasto
	 * 
	 * @param sector
	 * @param fileName
	 * @return
	 */
	String generaArtivoTxtLDFObjGasto(String query, String fileName);

	/**
	 * Estado Análitico del ejercicio del Presupuesto de Egresos Detallado - LDF
	 * Clasificación Administrativa
	 * 
	 * @param sector
	 * @param fileName
	 * @return
	 */
	String generaArtivoTxtLDFClasifAdminitrativa(String query, String fileName);

	/**
	 * Estado Análitico del ejercicio del Presupuesto de Egresos Detallado - LDF
	 * Servicios Personales por Categoría
	 * 
	 * @param sector
	 * @param fileName
	 * @return
	 */
	String generaArtivoTxtLDFServicosPersonales(String query, String fileName);

	/**
	 * Estado Análitico del ejercicio del Presupuesto de Egresos Detallado - LDF
	 * Clasificación Funcional
	 * 
	 * @param sector
	 * @param fileName
	 * @return
	 */
	String generaArtivoTxtLDFClasifFuncional(String query, String fileName);

	/**
	 * Matriz de Indicadores - Reporte TXT 01e
	 * 
	 * @param fileName
	 * @param idSector
	 * @return
	 */
	String generaReporteMatrizIndicadores1e(String fileName, Integer idSector);

	/**
	 * Ficha Tecnica de Seguimiento de Indicadores - Reporte TXT 08b
	 * 
	 * @param filename
	 * @param idsector
	 * @param trimestre
	 * @param cveOrganismo
	 * @return
	 */
	String generaReporteFichaSeguimiento8b(String filename, Integer idsector, Integer trimestre, String cveOrganismo);
	
	/**
	 * Ficha Tecnica de Diseño de Indicadores - Reporte TXT 01d
	 * 
	 * @param filename
	 * @param idsector
	 * @param trimestre
	 * @return
	 */
	String generaReporteFichaDiseno01d(String filename, Integer idsector, Integer trimestre);
}
