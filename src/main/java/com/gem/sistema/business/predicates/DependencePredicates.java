package com.gem.sistema.business.predicates;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.QCatdep;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencePredicates.
 *
 * @author Juan Carlos Pedraza Alcala
 */
public class DependencePredicates {

	/** The Constant IS_LAST_LEVEL_UPPER_CASE. */
	private static final String IS_LAST_LEVEL_UPPER_CASE = "S";

	/** The Constant IS_LAST_LEVEL_TOLOWER_CASE. */
	private static final String IS_LAST_LEVEL_TOLOWER_CASE = "s";

	/**
	 * Instantiates a new dependence predicates.
	 */
	public DependencePredicates() {
	}

	/**
	 * Gets the predicatet niv cta.
	 *
	 * @return the predicatet niv cta
	 */
	public static Predicate getPredicatetNivCta() {
		return QCatdep.catdep.ultniv.eq(IS_LAST_LEVEL_TOLOWER_CASE)
				.or(QCatdep.catdep.ultniv.eq(IS_LAST_LEVEL_UPPER_CASE));
	}

	/**
	 * Gets the predicatet niv cta.
	 *
	 * @param idSector the id sector
	 * @return the predicatet niv cta
	 */
	public static Predicate getPredicatetNivCta(Integer idSector) {
		return QCatdep.catdep.ultniv.eq(IS_LAST_LEVEL_TOLOWER_CASE)
				.or(QCatdep.catdep.ultniv.eq(IS_LAST_LEVEL_UPPER_CASE)).and(QCatdep.catdep.idsector.eq(idSector));
	}

	/**
	 * Gets the predicatet niv cta.
	 *
	 * @param cveDep the cve dep
	 * @param idSector the id sector
	 * @return the predicatet niv cta
	 */
	public static Predicate getPredicatetNivCta(String cveDep, Integer idSector) {
		return QCatdep.catdep.ultniv.equalsIgnoreCase(IS_LAST_LEVEL_TOLOWER_CASE)
				.and(QCatdep.catdep.idsector.eq(idSector)).and(QCatdep.catdep.clvdep.eq(cveDep));
	}

	/**
	 * Gets the predicatet cve dep.
	 *
	 * @param clvdep the clvdep
	 * @return the predicatet cve dep
	 */
	public static Predicate getPredicatetCveDep(String clvdep) {
		return QCatdep.catdep.clvdep.eq(clvdep);
	}

	/**
	 * Gets the predicatet by id sector.
	 *
	 * @param idSector the id sector
	 * @return the predicatet by id sector
	 */
	public static Predicate getPredicatetByIdSector(Integer idSector) {
		return QCatdep.catdep.idsector.eq(idSector);
	}

	/**
	 * Gets the predicatet by id sector.
	 *
	 * @param catdep the catdep
	 * @return the predicatet by id sector
	 */
	public static Predicate getPredicatetByIdSector(Catdep catdep) {
		BooleanExpression p = null;
		if (!StringUtils.isEmpty(catdep.getClvdep())) {
			p = QCatdep.catdep.clvdep.likeIgnoreCase("%" + catdep.getClvdep() + "%");
		}
		if (!StringUtils.isEmpty(catdep.getNomdep())) {
			if (p == null) {
				p = QCatdep.catdep.nomdep.likeIgnoreCase("%" + catdep.getNomdep() + "%");
			} else {
				p = p.and(QCatdep.catdep.nomdep.likeIgnoreCase("%" + catdep.getNomdep() + "%"));
			}
		}
		if (p == null) {
			p = QCatdep.catdep.idsector.eq(catdep.getIdsector());
		} else {
			p = p.and(QCatdep.catdep.idsector.eq(catdep.getIdsector()));
		}

		return p;
	}

	/**
	 * Gets the predicatet like cve dep.
	 *
	 * @param clvdep the clvdep
	 * @return the predicatet like cve dep
	 */
	public static Predicate getPredicatetLikeCveDep(String clvdep) {
		return QCatdep.catdep.clvdep.likeIgnoreCase(clvdep);
	}
}
