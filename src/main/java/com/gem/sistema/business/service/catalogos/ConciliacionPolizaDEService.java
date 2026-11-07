package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.vo.ConciliacionPolizaDEVO;

/**
 * Servicio de conciliacion de polizas de Diario contra Egreso por referencia en
 * cuentas de orden 82xx.
 *
 * @author Alfredo Neri
 */
public interface ConciliacionPolizaDEService {

	/**
	 * Obtiene las referencias del periodo con su poliza D y su poliza E.
	 *
	 * @param anopol
	 *            ejercicio contable
	 * @param mespol
	 *            mes del periodo
	 * @param idSector
	 *            sector del usuario en sesion
	 * @return lista de referencias; nunca nula
	 */
	List<ConciliacionPolizaDEVO> findByPeriodo(Integer anopol, Integer mespol, Integer idSector);

	/**
	 * Obtiene el ejercicio contable configurado para el sector.
	 *
	 * @param idSector
	 *            sector del usuario en sesion
	 * @return anio del ejercicio, o null si el sector no tiene configuracion
	 */
	Integer getAnioEjercicio(Integer idSector);

}
