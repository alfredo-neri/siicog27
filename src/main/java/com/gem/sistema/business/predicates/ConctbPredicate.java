package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QConctb;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class ConctbPredicate.
 */
public class ConctbPredicate {

	/**
	 * Gets the mes activo.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the mes activo
	 */
	public static Predicate getMesActivo(Integer idSector) {
		return QConctb.conctb.idsector.eq(idSector);
	}

	public static Predicate findByClaveAndIdsector(String clave, Integer idSector) {
		return QConctb.conctb.clave.eq(clave).and(QConctb.conctb.idsector.eq(idSector));
	}

}
