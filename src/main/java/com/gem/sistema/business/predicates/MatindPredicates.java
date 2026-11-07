package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QMatind;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class MatindPredicates.
 */
public class MatindPredicates {

	/**
	 * Instantiates a new matind predicates.
	 */
	private MatindPredicates() {
	}

	/**
	 * Exists matriz indicadores.
	 *
	 * @param clvdepg the clvdepg
	 * @param cveprog the cveprog
	 * @param cvetemas the cvetemas
	 * @return the predicate
	 */
	public static Predicate existsMatrizIndicadores(String clvdepg, String cveprog, String cvetemas) {
		return QMatind.matind.clvdepg.eq(clvdepg).and(QMatind.matind.cveprog.eq(cveprog))
				.and(QMatind.matind.cvetemas.eq(cvetemas));
	}
}
