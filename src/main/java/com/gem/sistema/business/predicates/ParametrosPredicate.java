package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcParametro;
import com.querydsl.core.types.Predicate;

/**
 * The Class ParametrosPredicate.
 */
public class ParametrosPredicate {

	public static Predicate findByDataType(String clvPassword, String dataType) {
		return QTcParametro.tcParametro.cveParametro.eq(clvPassword)
				.and(QTcParametro.tcParametro.dataType.eq(dataType));
	}

}
