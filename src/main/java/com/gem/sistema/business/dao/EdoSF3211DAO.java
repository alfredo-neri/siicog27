package com.gem.sistema.business.dao;

import java.math.BigDecimal;

import com.gem.sistema.business.dto.EdoSF3211DTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface EdoSF3211DAO.
 *
 * @author Dalia Tovar
 */

public interface EdoSF3211DAO {

	/**
	 * Creates the query cuenta.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	String createQueryCuenta(Integer idsector, Integer mesInicial, Integer mesFinal, Boolean mesAnteriorAcumulado);

	/**
	 * Execute query.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the edo SF 3211 DTO
	 */
	EdoSF3211DTO executeQuery(Integer idsector, Integer mesInicial, Integer mesFinal, Boolean mesAnteriorAcumulado);

	EdoSF3211DTO getTotalActualAndAnteriorByAnio(Integer idsector, Integer mesInicial);

	BigDecimal getTotalEjerciciosAnteriores(Integer idSector);

	EdoSF3211DTO getTotalForEfectivoByAnio(Integer mesFinalActual, Integer mesFinalAnterior);

	EdoSF3211DTO getAnteriorByAnio(Integer mesFinalActual, Integer mesFinalAnterior);
}
