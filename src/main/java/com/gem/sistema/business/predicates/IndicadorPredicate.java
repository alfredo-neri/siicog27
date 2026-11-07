package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QIndicadores;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadorPredicate.
 */
public class IndicadorPredicate {

	/**
	 * Find id sector.
	 *
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findIdSector(Integer idSector) {
		return QIndicadores.indicadores.idsector.eq(idSector);
	}

	/**
	 * Find id sector.
	 *
	 * @param idSector the id sector
	 * @param cveind the cveind
	 * @param perido the perido
	 * @return the predicate
	 */
	public static Predicate findIdSector(Integer idSector, Integer cveind, String perido) {
		return QIndicadores.indicadores.idsector.eq(idSector).and(QIndicadores.indicadores.cveind.eq(cveind))
				.and(QIndicadores.indicadores.periodo.equalsIgnoreCase(perido));
	}

}
