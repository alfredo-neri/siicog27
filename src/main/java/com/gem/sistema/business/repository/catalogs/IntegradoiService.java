package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import com.gem.sistema.business.domain.Integradoi;

// TODO: Auto-generated Javadoc
/**
 * The Interface IntegradoiService.
 */
public interface IntegradoiService {

	/**
	 * Find integrado by cuenta.
	 *
	 * @param cuenta the cuenta
	 * @param scta the scta
	 * @param sscta the sscta
	 * @param ssscta the ssscta
	 * @param sssscta the sssscta
	 * @param idSector the id sector
	 * @return the list
	 */
	public List<Integradoi> findIntegradoByCuenta(String cuenta, String scta, String sscta, String ssscta,
			String sssscta, Integer idSector);

	/**
	 * Find integrado cuenta.
	 *
	 * @param cuenta the cuenta
	 * @param scta the scta
	 * @param sscta the sscta
	 * @param ssscta the ssscta
	 * @param sssscta the sssscta
	 * @param idSector the id sector
	 * @return the list
	 */
	public List<Integradoi> findIntegradoCuenta(String cuenta, String scta, String sscta, String ssscta,
			String sssscta, Integer idSector);

}
