package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QPpMeta;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class PpMetaPredicates.
 */
public class PpMetaPredicates {

	/**
	 * Instantiates a new pp meta predicates.
	 */
	private PpMetaPredicates() {
	}

	/**
	 * Exists metas.
	 *
	 * @param cvldep the cvldep
	 * @return the predicate
	 */
	public static Predicate existsMetas(String cvldep) {
		return QPpMeta.ppMeta.clvdep.eq(cvldep);
	}

	/**
	 * Exist code.
	 *
	 * @param code the code
	 * @param clvDep the clv dep
	 * @param clvNep the clv nep
	 * @param idSector the id sector
	 * @return the predicate
	 */
	public static Predicate existCode(Integer code, String clvDep, String clvNep, Integer idSector) {
		return QPpMeta.ppMeta.clvmet.eq(code).and(QPpMeta.ppMeta.clvdep.eq(clvDep))
				.and(QPpMeta.ppMeta.clvnep.eq(clvNep)).and(QPpMeta.ppMeta.idsector.eq(idSector));
	}
}
