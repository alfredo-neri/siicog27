package com.gem.sistema.business.predicates;

import java.util.Date;

import com.gem.sistema.business.domain.QTcRegistraProcesoArchivo;
import com.gem.sistema.business.domain.QTcUsuario;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroProcesoArchivoPedicate.
 */
public class RegistroProcesoArchivoPedicate {
	
	/**
	 * Constructor por default.
	 */
	
	public RegistroProcesoArchivoPedicate (){};
	
	/**
	 * Find by fecha proceso.
	 *
	 * @param fechaProceso the fecha proceso
	 * @return the predicate
	 */
	public static Predicate findByFechaProceso(Date fechaProceso) {		
		//return QMaycta.maycta.cuenta.eq(cuenta);
		return QTcRegistraProcesoArchivo.tcRegistraProcesoArchivo.fechaProceso.eq(fechaProceso);
    }

}
