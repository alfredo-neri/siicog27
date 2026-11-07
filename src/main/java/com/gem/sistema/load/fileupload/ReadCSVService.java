package com.gem.sistema.load.fileupload;

import java.io.InputStream;

import com.gem.sistema.business.domain.Obra;
import com.gem.sistema.business.dto.ObrasCargaAutomaticaDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReadCSVService.
 */
public interface ReadCSVService {

	/**
	 * Process csv file.
	 *
	 * @param input the input
	 * @param errorMsg the error msg
	 * @return the obras carga automatica DTO
	 */
	ObrasCargaAutomaticaDTO processCsvFile(InputStream input, StringBuilder errorMsg);
	
	/**
	 * Valida programa.
	 *
	 * @param obra the obra
	 * @return the int
	 */
	int validaPrograma(Obra obra);
	
	/**
	 * Valida presupuesto.
	 *
	 * @param obra the obra
	 * @return the int
	 */
	int validaPresupuesto(Obra obra);
	
	/**
	 * Valida presupuesto manual.
	 *
	 * @param obra the obra
	 * @return the string
	 */
	String validaPresupuestoManual(Obra obra);
}
