package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcCuentasAdicionales;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class TcCuentasAdicionalesPredicates.
 */
public class TcCuentasAdicionalesPredicates {

	/**
	 * Find by cuenta.
	 *
	 * @param cuenta the cuenta
	 * @param adicional the adicional
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByCuenta(String cuenta, String adicional, Integer idSector) {
		BooleanExpression p = null;
		if (cuenta.substring(1, 1).equals("4")) {
			p = QTcCuentasAdicionales.tcCuentasAdicionales.cuenta.like(cuenta.substring(1, 1))
					.and(QTcCuentasAdicionales.tcCuentasAdicionales.ctaAdicional.eq("")
							.and(QTcCuentasAdicionales.tcCuentasAdicionales.idsector.eq(idSector)));
		} else {
			p = QTcCuentasAdicionales.tcCuentasAdicionales.cuenta.eq(cuenta)
					.and(QTcCuentasAdicionales.tcCuentasAdicionales.ctaAdicional.eq(adicional))
					.and(QTcCuentasAdicionales.tcCuentasAdicionales.idsector.eq(idSector));
		}
		return p;
	}

}
