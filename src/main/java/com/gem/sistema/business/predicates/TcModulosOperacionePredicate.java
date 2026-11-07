package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcModulosOperacione;
import com.gem.sistema.business.domain.TcModulosOperacione;
import com.mysema.query.types.Predicate;

public class TcModulosOperacionePredicate {

	public static Predicate findByprocesoAndSector(TcModulosOperacione tcModulosOperacione) {
		return QTcModulosOperacione.tcModulosOperacione.clvProceso.eq(tcModulosOperacione.getClvProceso())
				.and(QTcModulosOperacione.tcModulosOperacione.idSector.eq(tcModulosOperacione.getIdSector()));
	}

}
