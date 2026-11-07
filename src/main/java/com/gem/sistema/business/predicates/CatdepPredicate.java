package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QCatdep;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CatdepPredicate.
 */
public class CatdepPredicate {

	/**
	 * Find byclv dep.
	 *
	 * @param clvDep the clv dep
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate findByclvDep(String clvDep, Integer idSector) {
		return QCatdep.catdep.clvdep.eq(clvDep).and(QCatdep.catdep.idsector.eq(idSector));
	}

}
