package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcRegistraArchivoDigital;
import com.mysema.query.types.expr.NumberExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class TcRegistraArchivoDigitalPredicate.
 */
public class TcRegistraArchivoDigitalPredicate {

	/**
	 * Checks if is true digital.
	 *
	 * @param mes the mes
	 * @param tipo the tipo
	 * @param numero the numero
	 * @param idSector the id sector
	 * @return the number expression
	 */
	public static NumberExpression<Long> isTrueDigital(Integer mes, String tipo, Integer numero, Integer idSector) {
		
		return QTcRegistraArchivoDigital.tcRegistraArchivoDigital.mes.eq(String.valueOf(mes))
				.and(QTcRegistraArchivoDigital.tcRegistraArchivoDigital.tipo.eq(tipo))
				.and(QTcRegistraArchivoDigital.tcRegistraArchivoDigital.numero.eq(numero)).count();

	}

}
