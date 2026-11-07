package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.Maycta;

// TODO: Auto-generated Javadoc
/**
 * The Interface MayCtaService.
 */
public interface MayCtaService {

	/**
	 * Checks if is valid previous level.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid previous level
	 */
	boolean isValidPreviousLevel(Maycta catalog, StringBuilder errorMsg);

	/**
	 * Find first by cuenta.
	 *
	 * @param cuenta the cuenta
	 * @return the maycta
	 */
	Maycta findFirstByCuenta(String cuenta);

}
