package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.Proposito;
import com.gem.sistema.business.domain.QProposito;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class PropositoPredicates.
 */
public class PropositoPredicates {

	/**
	 * Instantiates a new proposito predicates.
	 */
	private PropositoPredicates() {
	}

	/**
	 * Exists proposito.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the predicate
	 */
	public static Predicate existsProposito(String clvdepg, String cveprog, String cvetemas) {
		return QProposito.proposito.clvdepg.eq(clvdepg).and(QProposito.proposito.cveprog.eq(cveprog))
				.and(QProposito.proposito.cvetemas.eq(cvetemas));
	}

	/**
	 * Fin prosito.
	 *
	 * @param cveDpe the cve dpe
	 * @param cvePro the cve pro
	 * @param tema   the tema
	 * @return the predicate
	 */
	public static Predicate finProsito(String cveDpe, String cvePro, String tema) {
		return QProposito.proposito.clvdepg.eq(cveDpe).and(QProposito.proposito.cveprog.eq(cvePro))
				.and(QProposito.proposito.cvetemas.eq(tema));
	}

	public static Predicate existeProposito(Proposito proposito) {
		return QProposito.proposito.clvdepg.eq(proposito.getClvdepg())
				.and(QProposito.proposito.cveprog.eq(proposito.getCveprog()))
				.and(QProposito.proposito.cvetemas.eq(proposito.getCvetemas()))
				.and(QProposito.proposito.cvepro.eq(proposito.getCvepro()))
				.and(QProposito.proposito.sectorid.eq(proposito.getSectorid()));
	}
}
