package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.Ftecnicadm;
import com.gem.sistema.business.domain.QFtecnicadm;
import com.mysema.query.types.Predicate;

public class FtecnicaDmPredicate {

	public static Predicate validaFichaTecnicaDiseno(Ftecnicadm ftecnicadm) {
		return QFtecnicadm.ftecnicadm.cvetemas.eq(ftecnicadm.getCvetemas())
				.and(QFtecnicadm.ftecnicadm.clvdep.eq(ftecnicadm.getClvdep()))
				.and(QFtecnicadm.ftecnicadm.clvfun.eq(ftecnicadm.getClvfun()))
				.and(QFtecnicadm.ftecnicadm.cveind.eq(ftecnicadm.getCveind()))
				.and(QFtecnicadm.ftecnicadm.idSector.eq(ftecnicadm.getIdSector()));
	}
}
