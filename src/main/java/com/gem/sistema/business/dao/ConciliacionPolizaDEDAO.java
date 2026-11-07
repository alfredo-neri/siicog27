package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.vo.ConciliacionPolizaDEVO;

/**
 * DAO para la conciliacion de polizas de Diario contra Egreso por referencia en
 * cuentas de orden 82xx.
 *
 * @author Alfredo Neri
 */
public interface ConciliacionPolizaDEDAO {

	/**
	 * Obtiene, para el periodo y sector indicados, una fila por cada referencia
	 * (REFPOL) de las cuentas de orden 82xx, con la poliza D y la poliza E
	 * colocadas lado a lado.
	 *
	 * @param anopol
	 *            ejercicio contable
	 * @param mespol
	 *            mes del periodo
	 * @param idSector
	 *            sector del usuario en sesion
	 * @return lista de referencias conciliadas y pendientes; nunca nula
	 */
	List<ConciliacionPolizaDEVO> findByPeriodo(Integer anopol, Integer mespol, Integer idSector);

}
