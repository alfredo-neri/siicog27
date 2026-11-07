package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QPpCapp;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class PpCappPredicates.
 */
public class PpCappPredicates {

	/**
	 * Instantiates a new pp capp predicates.
	 */
	private PpCappPredicates() {
	}

	/**
	 * Exist program.
	 *
	 * @param cvldep the cvldep
	 * @param clvnep the clvnep
	 * @return the predicate
	 */
	public static Predicate existProgram(String cvldep, String clvnep) {
		return QPpCapp.ppCapp.clvdep.eq(cvldep).and(QPpCapp.ppCapp.clvnep.eq(clvnep));
	}
}
