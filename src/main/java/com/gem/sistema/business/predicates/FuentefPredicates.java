package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.domain.QFuentef;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class FuentefPredicates.
 */
public class FuentefPredicates {

	/**
	 * Instantiates a new fuentef predicates.
	 */
	private FuentefPredicates() {
	}

	/**
	 * Elegible to programs.
	 *
	 * @return the predicate
	 */
	public static Predicate elegibleToPrograms() {
		return QFuentef.fuentef.liga.isNotEmpty();
	}

	/**
	 * Exist liga.
	 *
	 * @param liga the liga
	 * @return the predicate
	 */
	public static Predicate existLiga(String liga) {
		return QFuentef.fuentef.liga.eq(liga);
	}

	/**
	 * By id sector.
	 *
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate byIdSector(Integer idSector) {
		return QFuentef.fuentef.idsector.eq(idSector);
	}

	/**
	 * By id sector and liga.
	 *
	 * @param idSector the id sector
	 * @param liga the liga
	 * @return the predicate
	 */
	public static Predicate byIdSectorAndLiga(Integer idSector, String liga) {
		return QFuentef.fuentef.idsector.eq(idSector).and(QFuentef.fuentef.liga.eq(liga));
	}

	/**
	 * Find by cvef and id sector.
	 *
	 * @param idSector the id sector
	 * @param cvefuente the cvefuente
	 * @return the predicate
	 */
	public static Predicate findByCvefAndIdSector(Integer idSector, String cvefuente) {
		return QFuentef.fuentef.idsector.eq(idSector).and(QFuentef.fuentef.clvfte.eq(cvefuente));
	}

	/**
	 * By composite.
	 *
	 * @param fuentef the fuentef
	 * @return the predicate
	 */
	public static Predicate byComposite(Fuentef fuentef) {
		BooleanExpression p = null;
		if (StringUtils.isNotEmpty(fuentef.getLiga())) {
			p = QFuentef.fuentef.liga.likeIgnoreCase(fuentef.getLiga());
		}
		if (StringUtils.isNotEmpty(fuentef.getClvfte())) {
			if (p == null) {
				p = QFuentef.fuentef.clvfte.likeIgnoreCase(fuentef.getClvfte());
			} else {
				p = p.and(QFuentef.fuentef.clvfte.likeIgnoreCase(fuentef.getClvfte()));
			}
		}
		if (StringUtils.isNotEmpty(fuentef.getNombref())) {
			if (p == null) {
				p = QFuentef.fuentef.nombref.likeIgnoreCase(fuentef.getNombref());
			} else {
				p = p.and(QFuentef.fuentef.nombref.likeIgnoreCase(fuentef.getNombref()));
			}
		}
		if (null == p) {
			p = QFuentef.fuentef.idsector.eq(fuentef.getIdsector());
		} else {
			p = p.and(QFuentef.fuentef.idsector.eq(fuentef.getIdsector()));
		}
		return p;
	}
}
