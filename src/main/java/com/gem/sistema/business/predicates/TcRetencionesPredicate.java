package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcRetencione;
import com.mysema.query.types.Predicate;

public class TcRetencionesPredicate {

	public static Predicate findByIdSector(Long idSector) {
		return QTcRetencione.tcRetencione.idSector.eq(idSector);
	}

	public static Predicate findByIdSectorAndPolide(Long idSector, Long idPolide) {
		return QTcRetencione.tcRetencione.idSector.eq(idSector)
				.and(QTcRetencione.tcRetencione.idPolide.eq(Long.valueOf(idPolide)));
	}

}
