package com.gem.sistema.business.dao;

import com.gem.sistema.business.domain.Copome;
import com.gem.sistema.business.domain.Polien;

// TODO: Auto-generated Javadoc
/**
 * The Interface ActualizaNumeroPolizaDAO.
 */
public interface ActualizaNumeroPolizaDAO {
	
	/**
	 * Actualiza numero.
	 *
	 * @param polien the polien
	 * @param idSector the id sector
	 * @param user the user
	 * @return the copome
	 */
	Copome actualizaNumero(Polien polien, Integer idSector,String user);
	
	/**
	 * Actualiza numero 2.
	 *
	 * @param polien the polien
	 * @param idSector the id sector
	 * @param user the user
	 * @return the copome
	 */
	Copome actualizaNumero2(Polien polien, Integer idSector,String user);
}
