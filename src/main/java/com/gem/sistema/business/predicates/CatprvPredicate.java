package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.Catprv;
import com.gem.sistema.business.domain.QCatprv;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

// TODO: Auto-generated Javadoc
/**
 * The Class CatprvPredicate.
 */
public class CatprvPredicate {

	/**
	 * Instantiates a new catprv predicate.
	 */
	private CatprvPredicate() {
	}

	/**
	 * Find by id sector.
	 *
	 * @param catprv the catprv
	 * @return the predicate
	 */
	public static Predicate findByIdSector(Catprv catprv) {
		BooleanExpression p = null;
		if (catprv.getClvprv() != null) {
			p = QCatprv.catprv.clvprv.goe(catprv.getClvprv());
		}
		if (catprv.getTipprv() != null) {
			if (p == null) {
				p = QCatprv.catprv.tipprv.likeIgnoreCase(catprv.getTipprv());
			} else {
				p = p.and(QCatprv.catprv.tipprv.likeIgnoreCase(catprv.getTipprv()));
			}
		}

		if (catprv.getNomprv() != null) {
			if (p == null) {
				p = QCatprv.catprv.nomprv.likeIgnoreCase(catprv.getNomprv());
			} else {
				p = p.and(QCatprv.catprv.nomprv.likeIgnoreCase(catprv.getNomprv()));
			}
		}
		if (p == null) {
			p = QCatprv.catprv.idsector.eq(catprv.getIdsector());
		} else {
			p = p.and(QCatprv.catprv.idsector.eq(catprv.getIdsector()));
		}
		return p;
	}

}
