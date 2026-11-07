package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QPpMett;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class PpMettPredicates.
 */
public class PpMettPredicates {

	/**
	 * Instantiates a new pp mett predicates.
	 */
	private PpMettPredicates() {
	}

	/**
	 * Exists metas.
	 *
	 * @param cvldep the cvldep
	 * @return the predicate
	 */
	public static Predicate existsMetas(String cvldep) {
		return QPpMett.ppMett.clvdep.eq(cvldep);
	}
	
	/**
	 * Find by clvdep clv nep clv fuen clv met.
	 *
	 * @param clvDep the clv dep
	 * @param clvNep the clv nep
	 * @param clvFuen the clv fuen
	 * @param clvMet the clv met
	 * @return the predicate
	 */
	public static Predicate findByClvdepClvNepClvFuenClvMet(String clvDep, String clvNep, String clvFuen, Integer clvMet) {
		return QPpMett.ppMett.clvdep.eq(clvDep).and(QPpMett.ppMett.clvnep.eq(clvNep)).and(QPpMett.ppMett.clvfuen.eq(clvFuen)).and(QPpMett.ppMett.clvmet.eq(clvMet));
	}
}
