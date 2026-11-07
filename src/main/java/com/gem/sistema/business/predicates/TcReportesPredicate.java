package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcReporte;

import com.querydsl.core.types.Predicate;

public class TcReportesPredicate {

	public static Predicate findById(long idReporte) {
		return QTcReporte.tcReporte.idReporte.eq(idReporte);
	}

}
