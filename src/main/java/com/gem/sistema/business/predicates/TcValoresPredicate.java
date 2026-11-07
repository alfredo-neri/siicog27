package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcValores;
import com.mysema.query.types.Predicate;

public class TcValoresPredicate {

	public static Predicate findByIdTable(Long id) {
		return QTcValores.tcValores.idEtiqTabla.eq(id);
	}

}
