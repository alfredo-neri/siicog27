package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.ObrasDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ObrasService.
 *
 * @author DOOM
 */
public interface ObrasService {

	/**
	 * Buscar obras.
	 *
	 * @param programatica the programatica
	 * @param numObra the num obra
	 * @param nombreObra the nombre obra
	 * @return the list
	 */
	List<ObrasDTO> buscarObras(String programatica, String numObra, String nombreObra);
	
	/**
	 * Validar password borrar.
	 *
	 * @param pass the pass
	 * @return true, if successful
	 */
	boolean validarPasswordBorrar(String pass);

}
