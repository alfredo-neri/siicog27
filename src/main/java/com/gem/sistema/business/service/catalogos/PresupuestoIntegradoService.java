package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.domain.IntegradoEgreso;

// TODO: Auto-generated Javadoc
/**
 * The Interface PresupuestoIntegradoService.
 */
public interface PresupuestoIntegradoService {
	
	/**
	 * Exeute ingreso integrado.
	 *
	 * @param idUser the id user
	 * @param mes the mes
	 * @param cveRef the cve ref
	 * @param idEntidad the id entidad
	 * @return the integrado egreso
	 */
	
	IntegradoEgreso exeuteIngresoIntegrado(String idUser, Integer mes, Integer cveRef, Long idEntidad);
	
	/**
	 * Exeute presupuesto ingreso.
	 *
	 * @param idUser the id user
	 * @param mes the mes
	 * @param cveRef the cve ref
	 * @param idEntidad the id entidad
	 * @param idSector the id sector
	 * @return the integrado egreso
	 */
	IntegradoEgreso exeutePresupuestoIngreso(String idUser, Integer mes, Integer cveRef, Long idEntidad, Integer idSector);
	

}
