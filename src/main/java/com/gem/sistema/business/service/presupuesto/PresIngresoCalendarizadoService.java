package com.gem.sistema.business.service.presupuesto;

// TODO: Auto-generated Javadoc
/**
 * The Interface PresIngresoCalendarizadoService.
 */
public interface PresIngresoCalendarizadoService {
	
	/**
	 * Generar archivo ingresos 8110.
	 *
	 * @param nombreUsuario the nombre usuario
	 * @return the string
	 */
	String generarArchivoIngresos8110(String nombreUsuario);
	
	/**
	 * Gets the nombre archivo.
	 *
	 * @param username the username
	 * @return the nombre archivo
	 */
	String getNombreArchivo(String username);
}
