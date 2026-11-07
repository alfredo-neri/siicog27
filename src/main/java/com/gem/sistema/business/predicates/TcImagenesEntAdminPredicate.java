package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QTcImagenesEntAdmin;
import com.mysema.query.types.Predicate;

public class TcImagenesEntAdminPredicate {

	public static Predicate findByClvmunAndEntidad(Long idEntida, Integer clvMun) {
		return QTcImagenesEntAdmin.tcImagenesEntAdmin.idEntidadAdmin.eq(idEntida)
				.and(QTcImagenesEntAdmin.tcImagenesEntAdmin.clvmun.eq(clvMun));
	}

}
