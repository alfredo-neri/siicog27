package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QPpCaptura;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class PpCapturaPredicates.
 */
public class PpCapturaPredicates {

	/**
	 * Instantiates a new pp captura predicates.
	 */
	private PpCapturaPredicates() {
	}

	/**
	 * Exist program.
	 *
	 * @param cvldep the cvldep
	 * @param clvnep the clvnep
	 * @return the predicate
	 */
	public static Predicate existProgram(String cvldep, String clvnep) {
		return QPpCaptura.ppCaptura.clvdep.eq(cvldep).and(QPpCaptura.ppCaptura.clvnep.eq(clvnep));
	}
}
