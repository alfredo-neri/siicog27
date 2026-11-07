package com.gem.sistema.business.dao;

import com.gem.sistema.business.dto.ReconducPresupEgrIngrDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReconducPresupEgrIngrDAO.
 *
 * @author Gerardo CUERO
 */
public interface ReconducPresupEgrIngrDAO {

	/**
	 * Gets the by month user.
	 *
	 * @param month the month
	 * @param user the user
	 * @return the by month user
	 */
	ReconducPresupEgrIngrDTO getByMonthUser(Integer month, String user);
}
