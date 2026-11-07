package com.gem.sistema.business.service.presupuesto;

import com.gem.sistema.business.dto.ReconducPresupEgrIngrDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReconducPresupEgrIngrService.
 */
public interface ReconducPresupEgrIngrService {


	/**
	 * Gets the zipped file.
	 *
	 * @param mes the mes
	 * @param user the user
	 * @return the zipped file
	 */
	public ReconducPresupEgrIngrDTO getZippedFile(int mes, String user);

}
