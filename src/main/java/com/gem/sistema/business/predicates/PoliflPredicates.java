package com.gem.sistema.business.predicates;

import java.math.BigDecimal;

import com.gem.sistema.business.domain.QPolifl;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class PoliflPredicates.
 *
 * @author Gerardo CUERO
 */
public class PoliflPredicates {

	/**
	 * Exist.
	 *
	 * @param tipo the tipo
	 * @param mes the mes
	 * @param numero the numero
	 * @param renpol the renpol
	 * @param clvflu the clvflu
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate exist(String tipo, Integer mes, Integer numero, BigDecimal renpol, BigDecimal clvflu,
			Integer idSector) {
		return QPolifl.polifl.tippol.eq(tipo).and(QPolifl.polifl.mespol.eq(mes)).and(QPolifl.polifl.conpol.eq(numero)).and(QPolifl.polifl.renpol.eq(renpol))
				.and(QPolifl.polifl.clvflu.eq(clvflu)).and(QPolifl.polifl.idsector.eq(idSector));
	}
}
