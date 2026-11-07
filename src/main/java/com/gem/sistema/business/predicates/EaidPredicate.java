package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.Eaid;
import com.gem.sistema.business.domain.QEaid;
import com.mysema.query.types.Predicate;

public class EaidPredicate {

	public static Predicate validaEaid(Eaid eaid) {
		return QEaid.eaid.trimestre.eq(eaid.getTrimestre()).and(QEaid.eaid.consecutivo.eq(eaid.getConsecutivo()))
				.and(QEaid.eaid.idSector.eq(eaid.getIdSector()));

	}

}
