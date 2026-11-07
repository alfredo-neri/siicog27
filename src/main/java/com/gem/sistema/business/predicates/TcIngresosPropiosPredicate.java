package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcIngresosPropio;
import com.mysema.query.types.Predicate;

public class TcIngresosPropiosPredicate {

	public static Predicate findByClave(Integer clave) {
		return QTcIngresosPropio.tcIngresosPropio.clave.eq(clave);
	}

}
