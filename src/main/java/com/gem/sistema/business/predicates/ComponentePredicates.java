package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.Componente;
import com.gem.sistema.business.domain.QComponente;
import com.gem.sistema.business.domain.QProposito;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentePredicates.
 */
public class ComponentePredicates {

	/**
	 * Instantiates a new componente predicates.
	 */
	private ComponentePredicates() {
	}

	/**
	 * Exists componente.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the predicate
	 */
	public static Predicate existsComponente(String clvdepg, String cveprog, String cvetemas) {
		return QComponente.componente.clvdepg.eq(clvdepg).and(QComponente.componente.cveprog.eq(cveprog))
				.and(QComponente.componente.cvetemas.eq(cvetemas));
	}

	public static Predicate existecomponente(Componente componente) {
		return QComponente.componente.clvdepg.eq(componente.getClvdepg())
				.and(QComponente.componente.cveprog.eq(componente.getCveprog()))
				.and(QComponente.componente.cvetemas.eq(componente.getCvetemas()))
				.and(QComponente.componente.cvecom.eq(componente.getCvecom()))
				.and(QComponente.componente.sectorid.eq(componente.getSectorid()));
	}

}
