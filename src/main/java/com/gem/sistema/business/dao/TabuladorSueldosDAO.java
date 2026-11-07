package com.gem.sistema.business.dao;

import com.gem.sistema.business.domain.Tsueldo;

// TODO: Auto-generated Javadoc
/**
 * The Interface TabuladorSueldosDAO.
 */
public interface TabuladorSueldosDAO {
	
	/**
	 * Execute.
	 *
	 * @param fileName the file name
	 * @param pathFile the path file
	 * @param idUser the id user
	 * @param idSectpor the id sectpor
	 * @return the tsueldo
	 */
	Tsueldo execute(String fileName, String pathFile, String idUser, Integer idSectpor);
}
