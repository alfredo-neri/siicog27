package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Muninep;

// TODO: Auto-generated Javadoc
/**
 * The Interface DependenciesService.
 */
public interface DependenciesService {

	/**
	 * Checks if is valid previous level.
	 *
	 * @param catalogo the catalogo
	 * @param errorMsg the error msg
	 * @return true, if is valid previous level
	 */
	boolean isValidPreviousLevel(Catdep catalogo, StringBuilder errorMsg);
	
	/**
	 * Checks if is program depen.
	 *
	 * @param catdep the catdep
	 * @return true, if is program depen
	 */
	boolean isProgramDepen(Catdep catdep);
	
	/**
	 * Checks if is valid previous level.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @param idsector the idsector
	 * @return true, if is valid previous level
	 */
	boolean isValidPreviousLevel(final Muninep catalog, final StringBuilder errorMsg, Integer idsector);
	
	/**
	 * Tiene sucesores.
	 *
	 * @param catdep the catdep
	 * @param lista the lista
	 * @return true, if successful
	 */
	boolean tieneSucesores(Catdep catdep, List<Catdep> lista);
}