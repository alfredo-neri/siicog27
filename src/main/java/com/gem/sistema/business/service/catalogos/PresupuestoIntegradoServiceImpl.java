package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.PresupuestoEgresosIntegradoDAO;

import com.gem.sistema.business.domain.IntegradoEgreso;

// TODO: Auto-generated Javadoc
/**
 * The Class PresupuestoIntegradoServiceImpl.
 */
@Service(value="presupuestoIntegradoService")
public class PresupuestoIntegradoServiceImpl implements PresupuestoIntegradoService{
	
	/** The presupuesto egreso integrado DAO. */
	@Autowired
	private PresupuestoEgresosIntegradoDAO presupuestoEgresoIntegradoDAO;
	
	/**
	 * Gets the presupuesto egreso integrado DAO.
	 *
	 * @return the presupuesto egreso integrado DAO
	 */
	public PresupuestoEgresosIntegradoDAO getPresupuestoEgresoIntegradoDAO() {
		return presupuestoEgresoIntegradoDAO;
	}
	
	/**
	 * Sets the presupuesto egreso integrado DAO.
	 *
	 * @param presupuestoEgresoIntegradoDAO the new presupuesto egreso integrado DAO
	 */
	public void setPresupuestoEgresoIntegradoDAO(PresupuestoEgresosIntegradoDAO presupuestoEgresoIntegradoDAO) {
		this.presupuestoEgresoIntegradoDAO = presupuestoEgresoIntegradoDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PresupuestoIntegradoService#exeuteIngresoIntegrado(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long)
	 */
	@Override
	public IntegradoEgreso exeuteIngresoIntegrado(String idUser, Integer mes, Integer cveRef, Long idEntidad) {
		return this.presupuestoEgresoIntegradoDAO.executePresupuestoIntegrado(idUser, mes, cveRef, idEntidad);
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.PresupuestoIntegradoService#exeutePresupuestoIngreso(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public IntegradoEgreso exeutePresupuestoIngreso(String idUser, Integer mes, Integer cveRef, Long idEntidad, Integer idSector) {
		return this.presupuestoEgresoIntegradoDAO.exeuctePresupuesttoIngreso(idUser, mes, cveRef, idEntidad, idSector);
	}

}
