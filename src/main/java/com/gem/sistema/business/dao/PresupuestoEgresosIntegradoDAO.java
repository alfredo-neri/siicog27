package com.gem.sistema.business.dao;

import com.gem.sistema.business.domain.IntegradoEgreso;

// TODO: Auto-generated Javadoc
/**
 * The Interface PresupuestoEgresosIntegradoDAO.
 */
public interface PresupuestoEgresosIntegradoDAO {
	
	/*
	 * 
	 * 
	 * 
	 * */
	
	/**
	 * Execute presupuesto integrado.
	 *
	 * @param idUser the id user
	 * @param mes the mes
	 * @param cveFef the cve fef
	 * @param idEntidad the id entidad
	 * @return the integrado egreso
	 */
	IntegradoEgreso executePresupuestoIntegrado(String idUser, Integer mes, Integer cveFef, Long idEntidad);
	
	/**
	 * Exeucte presupuestto ingreso.
	 *
	 * @param idUser the id user
	 * @param mes the mes
	 * @param cveFef the cve fef
	 * @param idEntidad the id entidad
	 * @param idSector the id sector
	 * @return the integrado egreso
	 */
	IntegradoEgreso exeuctePresupuesttoIngreso(String idUser, Integer mes, Integer cveFef, Long idEntidad, Integer idSector);
	
	

}
