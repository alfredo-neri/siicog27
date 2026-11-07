package com.gem.sistema.business.predicates;


import com.gem.sistema.business.domain.QProgramamun;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class ProgramamunPredicate.
 */
public class ProgramamunPredicate {
	
	/**
	 * Find by all.
	 *
	 * @param cveDePg the cve de pg
	 * @return the predicate
	 */
	public static Predicate findByAll(String cveDePg) {
		return QProgramamun.programamun.cvedepg.eq(cveDePg);
    }

}
