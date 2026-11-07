package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QCpd;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CpdPredicate.
 */
public class CpdPredicate {

	/**
	 * Find by cve tema.
	 *
	 * @param cveTema the cve tema
	 * @return the predicate
	 */
	public static Predicate findByCveTema(String cveTema) {
		return QCpd.cpd.cvetemas.eq(cveTema);
	}

	public static Predicate findByCveTemasLength() {
		return QCpd.cpd.cvetemas.length().eq(8);
	}

}
