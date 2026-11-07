package com.gem.sistema.business.dao;


import com.gem.sistema.business.dto.PresupuestoDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PresupuestoJDBCTemplateDAO.
 */
public interface PresupuestoJDBCTemplateDAO {

	/**
	 * Carga archivos egresos.
	 *
	 * @param presupuesto the presupuesto
	 * @return the presupuesto DTO
	 */
	PresupuestoDTO cargaArchivosEgresos(PresupuestoDTO presupuesto);
	
	/**
	 * Carga archivos ingresos.
	 *
	 * @param presupuesto the presupuesto
	 * @return the presupuesto DTO
	 */
	PresupuestoDTO cargaArchivosIngresos(PresupuestoDTO presupuesto);

}
