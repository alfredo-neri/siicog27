package com.gem.sistema.business.dao;

import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliza;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolizaJDBCTemplateDAO.
 */
public interface PolizaJDBCTemplateDAO {

	/**
	 * Metodo que realiza el llamado al store procedure de copia de poliza.
	 *
	 * @param poliza            .
	 * @param polizaDestino the poliza destino
	 * @param userId the user id
	 * @param idSector the id sector
	 * @return .
	 */
	Poliza copyPoliza(Poliza poliza, Poliza polizaDestino, String userId, int idSector);
	
	/**
	 * Delete poliza.
	 *
	 * @param polien the polien
	 * @param idSector the id sector
	 * @param idUser the id user
	 * @return the poliza
	 */
	Poliza deletePoliza(Polien polien, int idSector, String idUser);
	
	/**
	 * Inverse poliza.
	 *
	 * @param poliza the poliza
	 * @param polizaDest the poliza dest
	 * @param userId the user id
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza inversePoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector);
	
	/**
	 * Negative poliza.
	 *
	 * @param poliza the poliza
	 * @param polizaDest the poliza dest
	 * @param userId the user id
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza negativePoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector);
	
	/**
	 * Cierre mensual.
	 *
	 * @param mes the mes
	 * @param user the user
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza cierreMensual(Integer mes, String user, int idSector);
	
	/**
	 * Desfa poliza.
	 *
	 * @param mes the mes
	 * @param tipo the tipo
	 * @param numero the numero
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza desfaPoliza(Integer mes, String tipo, Integer numero,  Integer idSector);
	
	/**
	 * Desafectacion.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza desafectacion(Integer mes,  int idSector);
	
	/**
	 * Afectacion pre cierre.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @param idUser the id user
	 * @return the poliza
	 */
	Poliza afectacionPreCierre(Integer mes,  Integer idSector, String idUser);
	
	/**
	 * Desafectacion preciere.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza desafectacionPreciere(Integer mes, int idSector);

}
